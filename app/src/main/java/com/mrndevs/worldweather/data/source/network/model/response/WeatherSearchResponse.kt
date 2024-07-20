package com.mrndevs.worldweather.data.source.network.model.response

import com.google.gson.annotations.SerializedName

data class WeatherSearchResponse(
    @SerializedName("country")
    val country: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("region")
    val region: String = "",
    @SerializedName("url")
    val url: String = ""
)