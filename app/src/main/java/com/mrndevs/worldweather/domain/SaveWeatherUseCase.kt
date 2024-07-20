package com.mrndevs.worldweather.domain

import com.mrndevs.worldweather.data.repository.WeatherRepository
import com.mrndevs.worldweather.data.source.local.model.WeatherEntity
import javax.inject.Inject

class SaveWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(weatherEntity: WeatherEntity) = repository.saveWeather(weatherEntity)
}