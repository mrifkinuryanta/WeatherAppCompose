package com.mrndevs.weatherapp.domain

import com.mrndevs.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherFromLocalUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getWeather()
}