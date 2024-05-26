package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.ui.component.ShimmerEffect
import com.mrndevs.weatherapp.ui.theme.SP20
import com.mrndevs.weatherapp.ui.theme.grey
import com.mrndevs.weatherapp.util.Util.addDegreeSymbol

@Composable
fun WeatherTempMinMax(forecast: WeatherEntity.Forecast, isLoading: Boolean = false) {
    val day = forecast.forecastDay.firstOrNull()?.day
    val maxTempt = day?.maxTempC ?: 0.0
    val minTemp = day?.minTempC ?: 0.0

    if (isLoading) {
        ShimmerEffect(width = 80.dp)
    } else {
        Text(text = buildAnnotatedString {
            withStyle(style = SP20.copy(Color.White).toSpanStyle()) {
                append(minTemp.addDegreeSymbol())
            }
            withStyle(style = SP20.copy(color = grey).toSpanStyle()) {
                append(stringResource(R.string.placeholder_min_max_temp))
            }
            withStyle(style = SP20.copy(Color.White).toSpanStyle()) {
                append(maxTempt.addDegreeSymbol())
            }
        })
    }
}