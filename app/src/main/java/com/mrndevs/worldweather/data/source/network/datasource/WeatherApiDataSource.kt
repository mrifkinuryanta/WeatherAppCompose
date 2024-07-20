package com.mrndevs.worldweather.data.source.network.datasource

import com.mrndevs.worldweather.data.source.network.model.response.WeatherResponse
import com.mrndevs.worldweather.data.source.network.model.response.WeatherSearchResponse
import retrofit2.Response

interface WeatherApiDataSource {
    suspend fun getWeather(q: String): Response<WeatherResponse>

    suspend fun getLocation(q: String): Response<List<WeatherSearchResponse>>
}