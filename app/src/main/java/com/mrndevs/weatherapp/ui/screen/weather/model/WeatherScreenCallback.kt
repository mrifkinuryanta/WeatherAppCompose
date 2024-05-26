package com.mrndevs.weatherapp.ui.screen.weather.model

data class WeatherScreenCallback(
    val onNavigateToSetting: () -> Unit = {},
    val onSearch: (String) -> Unit = {},
    val onGetWeather: (String) -> Unit = {},
    val onResetSearchData: () -> Unit = {},
    val onShowLocationSheet: (Boolean) -> Unit = {}
)
