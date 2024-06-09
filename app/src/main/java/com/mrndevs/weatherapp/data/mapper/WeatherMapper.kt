package com.mrndevs.weatherapp.data.mapper

import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.data.source.local.model.WeatherSearchEntity
import com.mrndevs.weatherapp.data.source.local.model.WindDirectionEnum
import com.mrndevs.weatherapp.data.source.network.model.response.WeatherResponse
import com.mrndevs.weatherapp.data.source.network.model.response.WeatherSearchResponse
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapWeatherResponseToEntity(data: WeatherResponse): WeatherEntity {
        return WeatherEntity(
            location = WeatherEntity.Location(
                name = data.location.name,
                region = data.location.region,
                country = data.location.country,
                lat = data.location.lat,
                lon = data.location.lon,
                tzId = data.location.tzId,
                localtimeEpoch = data.location.localtimeEpoch
            ),
            current = WeatherEntity.Current(
                lastUpdateEpoch = data.current.lastUpdatedEpoch,
                tempC = data.current.tempC,
                tempF = data.current.tempF,
                feelsLikeC = data.current.feelsLikeC,
                feelsLikeF = data.current.feelsLikeF,
                isDay = data.current.isDay == 1,
                windKph = data.current.windKph,
                windMph = data.current.windMph,
                windDegree = data.current.windDegree,
                windDir = WindDirectionEnum.getWindDirection(data.current.windDir),
                pressureIn = data.current.pressureIn,
                pressureMb = data.current.pressureMb,
                humidity = data.current.humidity,
                cloud = data.current.cloud,
                uv = data.current.uv,
                code = data.current.condition.code,
                text = data.current.condition.text
            ),
            forecast = WeatherEntity.Forecast(
                forecastDay = data.forecast.forecastDay.map {
                    WeatherEntity.Forecast.ForecastDay(
                        dateEpoch = it.dateEpoch,
                        day = WeatherEntity.Forecast.ForecastDay.Day(
                            maxTempC = it.day.maxTempC,
                            maxTempF = it.day.maxTempF,
                            minTempC = it.day.minTempC,
                            minTempF = it.day.minTempF,
                            maxWindKph = it.day.maxWindKph,
                            maxWindMph = it.day.maxWindMph,
                            code = it.day.condition.code
                        ),
                        hour = it.hour.map { hour ->
                            WeatherEntity.Forecast.ForecastDay.Hour(
                                timeEpoch = hour.timeEpoch,
                                tempC = hour.tempC,
                                tempF = hour.tempF,
                                isDay = hour.isDay == 1,
                                code = hour.condition.code
                            )
                        }
                    )
                }
            )
        )
    }

    fun mapWeatherSearchResponseToEntity(data: List<WeatherSearchResponse>): List<WeatherSearchEntity> {
        return data.map { item ->
            WeatherSearchEntity(
                id = item.id,
                name = item.name,
                region = item.region,
                country = item.country,
                lat = item.lat,
                lon = item.lon,
                url = item.url
            )
        }
    }
}