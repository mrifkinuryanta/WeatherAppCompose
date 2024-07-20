package com.mrndevs.worldweather.data.repository

import com.mrndevs.worldweather.base.Result
import com.mrndevs.worldweather.data.mapper.WeatherMapper
import com.mrndevs.worldweather.data.source.local.datasource.LocalDataSource
import com.mrndevs.worldweather.data.source.local.model.SettingsEntity
import com.mrndevs.worldweather.data.source.local.model.WeatherData
import com.mrndevs.worldweather.data.source.local.model.WeatherEntity
import com.mrndevs.worldweather.data.source.local.model.WeatherSearchEntity
import com.mrndevs.worldweather.data.source.network.datasource.WeatherApiDataSource
import com.mrndevs.worldweather.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val weatherApiDataSource: WeatherApiDataSource,
    private val weatherMapper: WeatherMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {
    private val responseBodyNull = "Response body is null"
    private val responseNotSuccessful = "Response is not successful"

    override fun saveWeather(weatherEntity: WeatherEntity): Flow<Boolean> {
        return localDataSource.saveWeather(weatherEntity)
    }

    override fun getWeather(): Flow<WeatherData?> = flow {
        val settings = localDataSource.getSettings().first()
        val data = localDataSource.getWeather().first()
        if (data != null) {
            emit(weatherMapper.mapWeatherResponseToWeatherData(data, settings))
        } else {
            emit(null)
        }
    }

    override fun saveSettings(data: SettingsEntity?): Flow<Boolean> {
        return localDataSource.saveSettings(data)
    }

    override fun getSettings(): Flow<SettingsEntity?> {
        return localDataSource.getSettings()
    }

    override fun getWeather(location: String): Flow<Result<WeatherEntity>> =
        flow {
            val response = weatherApiDataSource.getWeather(location)
            if (response.isSuccessful) {
                val resBody = response.body()
                if (resBody != null) {
                    val data = weatherMapper.mapWeatherResponseToEntity(resBody)
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error(responseBodyNull))
                }
            } else {
                emit(Result.Error(responseNotSuccessful))
            }
        }.catch {
            emit(Result.Error("Error: ${it.message}"))
        }.flowOn(ioDispatcher)

    override fun searchLocation(query: String): Flow<Result<List<WeatherSearchEntity>>> =
        flow {
            val response = weatherApiDataSource.getLocation(query)
            if (response.isSuccessful) {
                val resBody = response.body()
                if (resBody != null) {
                    val data = weatherMapper.mapWeatherSearchResponseToEntity(resBody)
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error(responseBodyNull))
                }
            } else {
                emit(Result.Error(responseNotSuccessful))
            }
        }.catch {
            emit(Result.Error("Error: ${it.message}"))
        }.flowOn(ioDispatcher)
}