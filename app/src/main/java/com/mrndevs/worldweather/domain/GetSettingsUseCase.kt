package com.mrndevs.worldweather.domain

import com.mrndevs.worldweather.data.repository.WeatherRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke() = weatherRepository.getSettings()
}