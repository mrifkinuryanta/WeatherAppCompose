package com.mrndevs.weatherapp.di

import com.mrndevs.weatherapp.data.mapper.WeatherMapper
import com.mrndevs.weatherapp.data.repository.WeatherRepository
import com.mrndevs.weatherapp.data.repository.WeatherRepositoryImpl
import com.mrndevs.weatherapp.data.source.local.datasource.LocalDataSource
import com.mrndevs.weatherapp.data.source.network.datasource.WeatherApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        localDataSource: LocalDataSource,
        weatherApiDataSource: WeatherApiDataSource,
        weatherMapper: WeatherMapper,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            localDataSource = localDataSource,
            weatherApiDataSource = weatherApiDataSource,
            weatherMapper = weatherMapper,
            ioDispatcher = ioDispatcher
        )
    }
}