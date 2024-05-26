package com.mrndevs.weatherapp.data.source.local.model

data class WeatherSearchEntity(
    val country: String = "",
    val id: Int = 0,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val name: String = "",
    val region: String = "",
    val url: String = ""
)
