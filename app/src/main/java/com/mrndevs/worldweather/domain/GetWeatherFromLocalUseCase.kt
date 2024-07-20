package com.mrndevs.worldweather.domain

import com.mrndevs.worldweather.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherFromLocalUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getWeather()
}