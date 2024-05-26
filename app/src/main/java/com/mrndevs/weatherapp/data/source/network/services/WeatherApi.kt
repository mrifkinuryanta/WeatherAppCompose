package com.mrndevs.weatherapp.data.source.network.services

import com.mrndevs.weatherapp.data.source.network.model.response.WeatherResponse
import com.mrndevs.weatherapp.data.source.network.model.response.WeatherSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: Int = 3,
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "yes"
    ): Response<WeatherResponse>

    @GET("search.json")
    suspend fun getLocation(
        @Query("key") key: String,
        @Query("q") q: String
    ): Response<List<WeatherSearchResponse>>
}