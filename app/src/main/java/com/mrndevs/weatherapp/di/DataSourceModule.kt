package com.mrndevs.weatherapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mrndevs.weatherapp.data.source.local.datasource.LocalDataSource
import com.mrndevs.weatherapp.data.source.local.datasource.LocalDataSourceImpl
import com.mrndevs.weatherapp.data.source.network.datasource.WeatherApiDataSource
import com.mrndevs.weatherapp.data.source.network.datasource.WeatherApiDataSourceImpl
import com.mrndevs.weatherapp.data.source.network.services.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(
        dataStore: DataStore<Preferences>,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): LocalDataSource {
        return LocalDataSourceImpl(
            dataStore = dataStore,
            ioDispatcher = ioDispatcher
        )
    }

    @Provides
    @Singleton
    fun provideWeatherApiDataSource(weatherApi: WeatherApi): WeatherApiDataSource {
        return WeatherApiDataSourceImpl(weatherApi = weatherApi)
    }
}