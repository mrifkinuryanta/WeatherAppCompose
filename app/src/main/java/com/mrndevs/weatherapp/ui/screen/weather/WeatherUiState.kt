package com.mrndevs.weatherapp.ui.screen.weather

import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherData
import com.mrndevs.weatherapp.data.source.local.model.WeatherSearchEntity

data class WeatherUiState(
    val isLoading: Boolean = true,
    val isLoadingSearch: Boolean = false,
    val isShowLocationSheet: Boolean = false,
    val weatherData: WeatherData = WeatherData(),
    val settings: SettingsEntity? = SettingsEntity(),
    val searchQuery: String = "",
    var searchResult: List<WeatherSearchEntity> = emptyList()
)