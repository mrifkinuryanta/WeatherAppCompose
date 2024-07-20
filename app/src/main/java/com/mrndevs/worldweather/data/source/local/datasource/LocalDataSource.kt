package com.mrndevs.worldweather.data.source.local.datasource

import com.mrndevs.worldweather.data.source.local.model.SettingsEntity
import com.mrndevs.worldweather.data.source.local.model.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun saveWeather(data: WeatherEntity): Flow<Boolean>

    fun getWeather(): Flow<WeatherEntity?>

    fun saveSettings(data: SettingsEntity?): Flow<Boolean>

    fun getSettings(): Flow<SettingsEntity?>
}