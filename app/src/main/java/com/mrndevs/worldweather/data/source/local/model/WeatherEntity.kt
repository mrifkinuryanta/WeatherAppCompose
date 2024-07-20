package com.mrndevs.worldweather.data.source.local.model

data class WeatherEntity(
    val location: Location = Location(),
    val current: Current = Current(),
    val forecast: Forecast = Forecast()
) {
    data class Location(
        val name: String = "",
        val region: String = "",
        val country: String = "",
        val lat: Double = 0.0,
        val lon: Double = 0.0,
        val tzId: String = "",
        val localtimeEpoch: Int = 0
    )

    data class Current(
        val lastUpdateEpoch: Int = 0,
        val tempC: Double = 0.0,
        val tempF: Double = 0.0,
        val feelsLikeC: Double = 0.0,
        val feelsLikeF: Double = 0.0,
        val isDay: Boolean = false,
        val windKph: Double = 0.0,
        val windMph: Double = 0.0,
        val windDegree: Int = 0,
        val windDir: WindDirectionEnum = WindDirectionEnum.NORTH,
        val pressureIn: Double = 0.0,
        val pressureMb: Double = 0.0,
        val humidity: Int = 0,
        val cloud: Int = 0,
        val uv: Int = 0,
        val code: Int = 0,
        val text: String = ""
    )

    data class Forecast(
        val forecastDay: List<ForecastDay> = listOf()
    ) {
        data class ForecastDay(
            val dateEpoch: Int = 0,
            val day: Day = Day(),
            val hour: List<Hour> = listOf()
        ) {
            data class Day(
                val maxTempC: Double = 0.0,
                val maxTempF: Double = 0.0,
                val minTempC: Double = 0.0,
                val minTempF: Double = 0.0,
                val maxWindKph: Double = 0.0,
                val maxWindMph: Double = 0.0,
                val code: Int = 0
            )

            data class Hour(
                val timeEpoch: Int = 0,
                val tempC: Double = 0.0,
                val tempF: Double = 0.0,
                val isDay: Boolean = false,
                val code: Int = 0
            )
        }
    }
}
