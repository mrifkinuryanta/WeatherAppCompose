package com.mrndevs.weatherapp.ui.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrndevs.weatherapp.base.Result
import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.domain.GetLocationUseCase
import com.mrndevs.weatherapp.domain.GetSettingsUseCase
import com.mrndevs.weatherapp.domain.GetWeatherFromApiUseCase
import com.mrndevs.weatherapp.domain.GetWeatherFromLocalUseCase
import com.mrndevs.weatherapp.domain.SaveSettingsUseCase
import com.mrndevs.weatherapp.domain.SaveWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    val uiState = _uiState

    fun init() {
        getSettingsUseCase().onEach { result ->
            val settings = result ?: SettingsEntity()
            _uiState.value = _uiState.value.copy(settings = settings)

            if (settings.isFirstRunApp) {
                _uiState.value = _uiState.value.copy(
                    isShowLocationSheet = true
                )
            } else {
                getWeather()
            }
        }.launchIn(viewModelScope)
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
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            location = result.data.location,
                            currentWeather = result.data.current,
                            forecast = result.data.forecast
                        )
                    }

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
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        location = result.location,
                        currentWeather = result.current,
                        forecast = result.forecast
                    )
                }
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
                            isLoadingSearch = false,
                            searchResult = result.data
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

    private fun saveWeather(data: WeatherEntity) {
        saveWeatherUseCase(data).launchIn(viewModelScope)
    }

    private fun saveSettings(data: SettingsEntity) {
        saveSettingsUseCase(data).launchIn(viewModelScope)
    }

    fun updateSearchQuery(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }

        getLocation()
    }

    fun resetSearchData() {
        _uiState.update { it.copy(searchResult = emptyList()) }
    }

    fun showLocationSheet(state: Boolean) {
        _uiState.value = _uiState.value.copy(isShowLocationSheet = state)
    }
}