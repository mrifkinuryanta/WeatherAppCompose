package com.mrndevs.weatherapp.ui.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrndevs.weatherapp.base.Result
import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherData
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.domain.GetLocationUseCase
import com.mrndevs.weatherapp.domain.GetSettingsUseCase
import com.mrndevs.weatherapp.domain.GetWeatherFromApiUseCase
import com.mrndevs.weatherapp.domain.GetWeatherFromLocalUseCase
import com.mrndevs.weatherapp.domain.SaveSettingsUseCase
import com.mrndevs.weatherapp.domain.SaveWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.Instant
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

    fun init() {
        getSettings()
        uiState.value.settings?.let { settings ->
            val lastUpdate = settings.updateAt
            val currentTime = Instant.now().epochSecond
            val currentLocation = settings.currentLocation
            val location =
                if (currentTime - lastUpdate > 3600 && currentLocation.isNotBlank()) currentLocation else null
            if (settings.isFirstRunApp.not()) {
                getWeather(location)
            }
        }
    }

    fun getWeather(query: String? = null, isUpdate: Boolean = false) {
        when (query) {
            null -> getWeatherFromLocal()
            else -> getWeatherFromApi(query, isUpdate)
        }
    }

    private fun getWeatherFromApi(location: String, isUpdate: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getWeatherFromApiUseCase(location).onEach { result ->
            when (result) {
                is Result.Success -> {
                    val setting = uiState.value.settings?.copy(
                        isFirstRunApp = false,
                        isDarkTheme = result.data.current.isDay.not(),
                        currentTimeZone = result.data.location.tzId,
                        currentLocation = result.data.location.name,
                        updateAt = result.data.location.localtimeEpoch
                    )
                    saveSettings(setting)
                    saveWeather(result.data)
                    if (isUpdate) {
                        getWeatherFromLocal()
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
            _uiState.update { it.copy(isLoading = false, weatherData = result as WeatherData) }
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

    fun getSettings() {
        getSettingsUseCase().onEach { result ->
            val settings = result ?: uiState.value.settings
            _uiState.update { currentData ->
                currentData.copy(settings = settings)
            }
        }.launchIn(viewModelScope)
    }

    private fun saveWeather(data: WeatherEntity) {
        saveWeatherUseCase(data).launchIn(viewModelScope)
    }

    private fun saveSettings(data: SettingsEntity?) {
        saveSettingsUseCase(data).onEach {
            getSettings()
        }.launchIn(viewModelScope)
    }

    fun refreshWeather() {
        getWeather(uiState.value.weatherData.currentLocation.name, isUpdate = true)
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