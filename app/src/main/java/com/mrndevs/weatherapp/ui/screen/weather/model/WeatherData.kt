package com.mrndevs.weatherapp.ui.screen.weather.model

import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity

data class WeatherData(
    val currentTemp: Double = 0.0,
    val currentWindSpeed: Double = 0.0,
    val currentPressure: Double = 0.0,
    val currentFeelsLike: Double = 0.0,
    val currentMinTemp: Double = 0.0,
    val currentMaxTemp: Double = 0.0,
    val forecastMinTemp: Double = 0.0,
    val forecastMaxTemp: Double = 0.0,
    val forecastToday: List<WeatherEntity.Forecast.ForecastDay.Hour> = emptyList(),
)
