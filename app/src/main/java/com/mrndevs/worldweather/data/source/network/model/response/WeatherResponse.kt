package com.mrndevs.worldweather.data.source.network.model.response


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("alerts")
    val alerts: Alerts = Alerts(),
    @SerializedName("current")
    val current: Current = Current(),
    @SerializedName("forecast")
    val forecast: Forecast = Forecast(),
    @SerializedName("location")
    val location: Location = Location()
) {
    data class Current(
        @SerializedName("air_quality")
        val airQuality: AirQuality = AirQuality(),
        @SerializedName("cloud")
        val cloud: Int = 0,
        @SerializedName("condition")
        val condition: Condition = Condition(),
        @SerializedName("feelslike_c")
        val feelsLikeC: Double = 0.0,
        @SerializedName("feelslike_f")
        val feelsLikeF: Double = 0.0,
        @SerializedName("humidity")
        val humidity: Int = 0,
        @SerializedName("is_day")
        val isDay: Int = 0,
        @SerializedName("last_updated_epoch")
        val lastUpdatedEpoch: Int = 0,
        @SerializedName("pressure_in")
        val pressureIn: Double = 0.0,
        @SerializedName("pressure_mb")
        val pressureMb: Double = 0.0,
        @SerializedName("temp_c")
        val tempC: Double = 0.0,
        @SerializedName("temp_f")
        val tempF: Double = 0.0,
        @SerializedName("uv")
        val uv: Int = 0,
        @SerializedName("wind_degree")
        val windDegree: Int = 0,
        @SerializedName("wind_dir")
        val windDir: String = "",
        @SerializedName("wind_kph")
        val windKph: Double = 0.0,
        @SerializedName("wind_mph")
        val windMph: Double = 0.0
    ) {
        data class AirQuality(
            @SerializedName("co")
            val co: Float = 0.0f,
            @SerializedName("gb-defra-index")
            val gbDefraIndex: Int = 0,
            @SerializedName("no2")
            val no2: Float = 0.0f,
            @SerializedName("o3")
            val o3: Float = 0.0f,
            @SerializedName("pm10")
            val pm10: Float = 0.0f,
            @SerializedName("pm2_5")
            val pm25: Float = 0.0f,
            @SerializedName("so2")
            val so2: Float = 0.0f,
            @SerializedName("us-epa-index")
            val usEpaIndex: Int = 0
        )

        data class Condition(
            @SerializedName("code")
            val code: Int = 0,
            @SerializedName("text")
            val text: String = ""
        )
    }

    data class Forecast(
        @SerializedName("forecastday")
        val forecastDay: List<Forecastday> = listOf()
    ) {
        data class Forecastday(
            @SerializedName("date_epoch")
            val dateEpoch: Int = 0,
            @SerializedName("day")
            val day: Day = Day(),
            @SerializedName("hour")
            val hour: List<Hour> = listOf()
        ) {
            data class Day(
                @SerializedName("condition")
                val condition: Condition = Condition(),
                @SerializedName("maxtemp_c")
                val maxTempC: Double = 0.0,
                @SerializedName("maxtemp_f")
                val maxTempF: Double = 0.0,
                @SerializedName("maxwind_kph")
                val maxWindKph: Double = 0.0,
                @SerializedName("maxwind_mph")
                val maxWindMph: Double = 0.0,
                @SerializedName("mintemp_c")
                val minTempC: Double = 0.0,
                @SerializedName("mintemp_f")
                val minTempF: Double = 0.0,
            ) {
                data class Condition(
                    @SerializedName("code")
                    val code: Int = 0,
                    @SerializedName("text")
                    val text: String = ""
                )
            }

            data class Hour(
                @SerializedName("condition")
                val condition: Condition = Condition(),
                @SerializedName("is_day")
                val isDay: Int = 0,
                @SerializedName("temp_c")
                val tempC: Double = 0.0,
                @SerializedName("temp_f")
                val tempF: Double = 0.0,
                @SerializedName("time_epoch")
                val timeEpoch: Int = 0
            ) {
                data class Condition(
                    @SerializedName("code")
                    val code: Int = 0
                )
            }
        }
    }

    data class Location(
        @SerializedName("country")
        val country: String = "",
        @SerializedName("lat")
        val lat: Double = 0.0,
        @SerializedName("localtime")
        val localtime: String = "",
        @SerializedName("localtime_epoch")
        val localtimeEpoch: Int = 0,
        @SerializedName("lon")
        val lon: Double = 0.0,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("region")
        val region: String = "",
        @SerializedName("tz_id")
        val tzId: String = ""
    )

    data class Alerts(
        @SerializedName("alert")
        val alert: List<Alert> = listOf()
    ) {
        data class Alert(
            @SerializedName("areas")
            val areas: Any? = Any(),
            @SerializedName("category")
            val category: String = "",
            @SerializedName("certainty")
            val certainty: Any? = Any(),
            @SerializedName("desc")
            val desc: String = "",
            @SerializedName("effective")
            val effective: String = "",
            @SerializedName("event")
            val event: String = "",
            @SerializedName("expires")
            val expires: String = "",
            @SerializedName("headline")
            val headline: String = "",
            @SerializedName("instruction")
            val instruction: String = "",
            @SerializedName("msgtype")
            val msgtype: Any? = Any(),
            @SerializedName("note")
            val note: Any? = Any(),
            @SerializedName("severity")
            val severity: Any? = Any(),
            @SerializedName("urgency")
            val urgency: Any? = Any()
        )
    }
}
