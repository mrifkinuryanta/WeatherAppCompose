package com.mrndevs.worldweather.data.source.local.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.mrndevs.worldweather.data.source.local.model.SettingsEntity
import com.mrndevs.worldweather.data.source.local.model.WeatherEntity
import com.mrndevs.worldweather.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : LocalDataSource {

    override fun saveWeather(data: WeatherEntity): Flow<Boolean> = flow {
        try {
            dataStore.edit { preferences ->
                preferences[WEATHER.toPreferenceKey()] = Gson().toJson(data)
            }
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }.flowOn(ioDispatcher)

    override fun getWeather(): Flow<WeatherEntity?> = flow {
        dataStore.data.map { preferences ->
            Gson().fromJson(preferences[WEATHER.toPreferenceKey()], WeatherEntity::class.java)
        }.collect { result ->
            emit(result)
        }
    }.flowOn(ioDispatcher)

    override fun saveSettings(data: SettingsEntity?): Flow<Boolean> = flow {
        try {
            dataStore.edit { preferences ->
                preferences[SETTINGS.toPreferenceKey()] = Gson().toJson(data)
            }
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }.flowOn(ioDispatcher)

    override fun getSettings(): Flow<SettingsEntity?> = flow {
        dataStore.data.map { preferences ->
            Gson().fromJson(preferences[SETTINGS.toPreferenceKey()], SettingsEntity::class.java)
        }.collect { result ->
            emit(result)
        }
    }.flowOn(ioDispatcher)

    companion object {
        private const val WEATHER = "weather"
        private const val SETTINGS = "settings"

        fun String.toPreferenceKey() = stringPreferencesKey(this)
    }
}