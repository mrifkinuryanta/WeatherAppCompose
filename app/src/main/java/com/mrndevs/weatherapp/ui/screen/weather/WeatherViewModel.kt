package com.mrndevs.weatherapp.ui.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrndevs.weatherapp.base.Result
import com.mrndevs.weatherapp.data.source.local.model.PressureUnitEnum
import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.data.source.local.model.TempUnitEnum
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.data.source.local.model.WindSpeedUnitEnum
import com.mrndevs.weatherapp.domain.GetLocationUseCase
import com.mrndevs.weatherapp.domain.GetSettingsUseCase
import com.mrndevs.weatherapp.domain.GetWeatherFromApiUseCase
import com.mrndevs.weatherapp.domain.GetWeatherFromLocalUseCase
import com.mrndevs.weatherapp.domain.SaveSettingsUseCase
import com.mrndevs.weatherapp.domain.SaveWeatherUseCase
import com.mrndevs.weatherapp.ui.screen.weather.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherFromLocalUseCase: GetWeatherFromLocalUseCase,
    private val getWeatherFromApiUseCase: GetWeatherFromApiUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    init {
        checkFirstRunApp { getWeather() }
    }

    fun getWeather(query: String? = null) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        when (query) {
            null -> getWeatherFromLocal()
            else -> getWeatherFromApi(query)
        }
    }

    private fun getWeatherFromApi(location: String) {
        getWeatherFromApiUseCase(location).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoading = false) }

                    updateCurrentData(result.data)
                    saveWeather(result.data)

                    if (_uiState.value.settings.isFirstRunApp) {
                        val data = uiState.value.settings.copy(isFirstRunApp = false)
                        _uiState.value = _uiState.value.copy(settings = data)
                        saveSettings(data)
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getWeatherFromLocal() {
        getWeatherFromLocalUseCase().onEach { result ->
            if (result != null) {
                _uiState.update { it.copy(isLoading = false) }

                updateCurrentData(result)
            } else {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getLocation() {
        _uiState.value = _uiState.value.copy(isLoadingSearch = true)
        getLocationUseCase(uiState.value.searchQuery.trim()).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoadingSearch = false, searchResult = result.data
                        )
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(isLoadingSearch = false)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkFirstRunApp(onFalse: () -> Unit) {
        getSettingsUseCase().onEach { result ->
            val settings = result ?: SettingsEntity()
            _uiState.update { currentData ->
                currentData.copy(settings = settings)
            }

            if (settings.isFirstRunApp) {
                _uiState.value = _uiState.value.copy(
                    isShowLocationSheet = true
                )
            } else {
                onFalse()
            }
        }.launchIn(viewModelScope)
    }

    private fun updateCurrentData(data: WeatherEntity) {
        val settings = uiState.value.settings
        val oneHourBeforeCurrentTime = data.location.localtimeEpoch - 7200
        val hours = data.forecast.forecastDay.take(2).flatMap { it.hour }
        val twentyFourHoursList =
            hours.filter { it.timeEpoch >= oneHourBeforeCurrentTime }.take(24)

        val weatherData = if (settings.tempUnit == TempUnitEnum.CELSIUS) {
            WeatherData(
                currentTemp = data.current.tempC,
                currentFeelsLike = data.current.feelsLikeC,
                currentMinTemp = data.forecast.forecastDay.firstOrNull()?.day?.minTempC ?: 0.0,
                currentMaxTemp = data.forecast.forecastDay.firstOrNull()?.day?.maxTempC ?: 0.0,
                forecastMinTemp = data.forecast.forecastDay.minByOrNull { it.day.minTempC }?.day?.minTempC
                    ?: 0.0,
                forecastMaxTemp = data.forecast.forecastDay.maxByOrNull { it.day.maxTempC }?.day?.maxTempC
                    ?: 0.0
            )
        } else {
            WeatherData(
                currentTemp = data.current.tempF,
                currentFeelsLike = data.current.feelsLikeF,
                currentMinTemp = data.forecast.forecastDay.firstOrNull()?.day?.minTempF ?: 0.0,
                currentMaxTemp = data.forecast.forecastDay.firstOrNull()?.day?.maxTempF ?: 0.0,
                forecastMinTemp = data.forecast.forecastDay.minByOrNull { it.day.minTempF }?.day?.minTempF
                    ?: 0.0,
                forecastMaxTemp = data.forecast.forecastDay.maxByOrNull { it.day.maxTempF }?.day?.maxTempF
                    ?: 0.0
            )
        }

        val currentWindSpeed = if (settings.windSpeedUnit == WindSpeedUnitEnum.KPH) {
            data.current.windKph
        } else {
            data.current.windMph
        }

        val currentPressure =
            if (settings.pressureUnit != PressureUnitEnum.INHG) {
                data.current.pressureMb
            } else {
                data.current.pressureIn
            }

        _uiState.update { currentData ->
            currentData.copy(
                settings = currentData.settings.copy(isDarkTheme = data.current.isDay.not()),
                location = data.location,
                currentWeather = data.current,
                forecast = data.forecast,
                weatherData = weatherData.copy(
                    currentWindSpeed = currentWindSpeed,
                    currentPressure = currentPressure,
                    forecastToday = twentyFourHoursList
                )
            )
        }

        saveSettings(uiState.value.settings)
    }

    private fun saveWeather(data: WeatherEntity) {
        saveWeatherUseCase(data).launchIn(viewModelScope)
    }

    private fun saveSettings(data: SettingsEntity) {
        saveSettingsUseCase(data).launchIn(viewModelScope)
    }

    fun refreshWeather() {
        checkFirstRunApp { getWeather(uiState.value.location.name) }
    }

    fun updateSearchQuery(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }

        if (newQuery.isNotBlank()) {
            getLocation()
        }
    }

    fun resetSearchData() {
        _uiState.update { it.copy(searchResult = emptyList(), searchQuery = "") }
    }

    fun showLocationSheet(state: Boolean) {
        _uiState.value = _uiState.value.copy(isShowLocationSheet = state)
    }
}