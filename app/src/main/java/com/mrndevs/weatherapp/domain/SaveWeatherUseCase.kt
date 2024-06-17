package com.mrndevs.weatherapp.domain

import com.mrndevs.weatherapp.data.repository.WeatherRepository
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import javax.inject.Inject

class SaveWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(weatherEntity: WeatherEntity) = repository.saveWeather(weatherEntity)
}