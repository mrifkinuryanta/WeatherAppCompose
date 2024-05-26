package com.mrndevs.weatherapp.domain

import com.mrndevs.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherFromApiUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(location: String) = repository.getWeather(location)
}