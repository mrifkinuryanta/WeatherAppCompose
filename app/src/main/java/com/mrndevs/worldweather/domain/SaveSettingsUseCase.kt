package com.mrndevs.worldweather.domain

import com.mrndevs.worldweather.data.repository.WeatherRepository
import com.mrndevs.worldweather.data.source.local.model.SettingsEntity
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(data: SettingsEntity?) = repository.saveSettings(data)
}