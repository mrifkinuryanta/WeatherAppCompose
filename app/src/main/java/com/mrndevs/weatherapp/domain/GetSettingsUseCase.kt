package com.mrndevs.weatherapp.domain

import com.mrndevs.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke() = weatherRepository.getSettings()
}