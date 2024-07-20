package com.mrndevs.worldweather.data.source.network.datasource

import com.mrndevs.worldweather.BuildConfig
import com.mrndevs.worldweather.data.source.network.model.response.WeatherResponse
import com.mrndevs.worldweather.data.source.network.model.response.WeatherSearchResponse
import com.mrndevs.worldweather.data.source.network.services.WeatherApi
import retrofit2.Response

class WeatherApiDataSourceImpl(
    private val weatherApi: WeatherApi
) : WeatherApiDataSource {
    private val apiKey = BuildConfig.API_KEY

    override suspend fun getWeather(q: String): Response<WeatherResponse> =
        weatherApi.getWeather(apiKey, q)

    override suspend fun getLocation(q: String): Response<List<WeatherSearchResponse>> =
        weatherApi.getLocation(apiKey, q)
}