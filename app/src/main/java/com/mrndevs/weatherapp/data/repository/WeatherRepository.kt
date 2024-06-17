package com.mrndevs.weatherapp.data.repository

import com.mrndevs.weatherapp.base.Result
import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherData
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherSearchEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    /**
     * Get the weather data from the local data source
     * @return a flow of WeatherEntity
     */
    fun saveWeather(weatherEntity: WeatherEntity): Flow<Boolean>

    /**
     * Get the weather data from the local data source
     * @return a flow of WeatherEntity
     */
    fun getWeather(): Flow<WeatherData?>

    /**
     * Save the settings
     * @param data the settings to save
     * @return a flow of Boolean
     */
    fun saveSettings(data: SettingsEntity?): Flow<Boolean>

    /**
     * Get the settings
     * @return a flow of SettingsEntity
     */
    fun getSettings(): Flow<SettingsEntity?>

    /**
     * Get weather data from the API
     * @param location the location to get the weather data
     * @return a flow of Result<WeatherEntity>
     */
    fun getWeather(location: String): Flow<Result<WeatherEntity>>

    /**
     * Search for a location
     * @param query the query to search
     * @return a flow of Result<List<WeatherSearchEntity>>
     */
    fun searchLocation(query: String): Flow<Result<List<WeatherSearchEntity>>>
}