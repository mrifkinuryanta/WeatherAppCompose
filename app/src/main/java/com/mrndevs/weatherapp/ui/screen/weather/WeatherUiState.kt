package com.mrndevs.weatherapp.ui.screen.weather

import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherSearchEntity

data class WeatherUiState(
    val isLoading: Boolean = true,
    val isLoadingSearch: Boolean = false,
    val isShowLocationSheet: Boolean = false,
    val location: WeatherEntity.Location = WeatherEntity.Location(),
    val currentWeather: WeatherEntity.Current = WeatherEntity.Current(),
    val forecast: WeatherEntity.Forecast = WeatherEntity.Forecast(),
    val settings: SettingsEntity = SettingsEntity(),
    val searchQuery: String = "",
    var searchResult: List<WeatherSearchEntity> = emptyList()
)