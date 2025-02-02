package com.mrndevs.worldweather.ui.screen.weather.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.R
import com.mrndevs.worldweather.data.source.local.model.WeatherData
import com.mrndevs.worldweather.ui.component.ShimmerEffect
import com.mrndevs.worldweather.ui.theme.SP20
import com.mrndevs.worldweather.ui.theme.accordion
import com.mrndevs.worldweather.util.Util.addDegreeSymbol

@Composable
fun WeatherTempMinMax(weatherData: WeatherData, isLoading: Boolean = false) {
    val minTemp = weatherData.currentMinTemp
    val maxTemp = weatherData.currentMaxTemp

    if (isLoading) {
        ShimmerEffect(width = 80.dp)
    } else {
        Text(text = buildAnnotatedString {
            withStyle(style = SP20.copy(Color.White).toSpanStyle()) {
                append(minTemp.addDegreeSymbol())
            }
            withStyle(style = SP20.copy(color = accordion).toSpanStyle()) {
                append(stringResource(R.string.placeholder_min_max_temp))
            }
            withStyle(style = SP20.copy(Color.White).toSpanStyle()) {
                append(maxTemp.addDegreeSymbol())
            }
        })
    }
}