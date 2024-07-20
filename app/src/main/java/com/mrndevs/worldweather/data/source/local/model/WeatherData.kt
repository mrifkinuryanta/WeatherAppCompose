package com.mrndevs.worldweather.data.source.local.model

data class WeatherData(
    val currentTemp: Double = 0.0,
    val currentWindSpeed: Double = 0.0,
    val currentPressure: Double = 0.0,
    val currentFeelsLike: Double = 0.0,
    val currentMinTemp: Double = 0.0,
    val currentMaxTemp: Double = 0.0,
    val currentLocation: WeatherEntity.Location = WeatherEntity.Location(),
    val currentWeather: WeatherEntity.Current = WeatherEntity.Current(),
    val forecastMinTemp: Double = 0.0,
    val forecastMaxTemp: Double = 0.0,
    val forecastToday: List<WeatherEntity.Forecast.ForecastDay.Hour> = emptyList(),
    val forecastDay: List<WeatherEntity.Forecast.ForecastDay> = listOf()
)
