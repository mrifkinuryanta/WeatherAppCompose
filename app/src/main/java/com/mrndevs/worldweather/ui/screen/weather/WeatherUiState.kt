package com.mrndevs.worldweather.ui.screen.weather

import com.mrndevs.worldweather.data.source.local.model.SettingsEntity
import com.mrndevs.worldweather.data.source.local.model.WeatherData
import com.mrndevs.worldweather.data.source.local.model.WeatherSearchEntity

data class WeatherUiState(
    val isLoading: Boolean = true,
    val isLoadingSearch: Boolean = false,
    val isShowLocationSheet: Boolean = false,
    val isNoConnection: Boolean = false,
    val weatherData: WeatherData = WeatherData(),
    val settings: SettingsEntity? = SettingsEntity(),
    val searchQuery: String = "",
    var searchResult: List<WeatherSearchEntity> = emptyList()
)