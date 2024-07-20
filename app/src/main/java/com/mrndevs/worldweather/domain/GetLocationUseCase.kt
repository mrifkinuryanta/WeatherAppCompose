package com.mrndevs.worldweather.domain

import com.mrndevs.worldweather.data.repository.WeatherRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke(query: String) = weatherRepository.searchLocation(query)
}