package com.mrndevs.weatherapp.domain

import com.mrndevs.weatherapp.data.repository.WeatherRepository
import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(data: SettingsEntity) = repository.saveSettings(data)
}