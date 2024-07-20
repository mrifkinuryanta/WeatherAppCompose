@file:OptIn(ExperimentalLayoutApi::class)

package com.mrndevs.worldweather.ui.screen.weather.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrndevs.worldweather.R
import com.mrndevs.worldweather.data.source.local.model.UltraVioletEnum
import com.mrndevs.worldweather.data.source.local.model.WindDirectionEnum
import com.mrndevs.worldweather.ui.component.CardItem
import com.mrndevs.worldweather.ui.component.CircleBackground
import com.mrndevs.worldweather.ui.component.Compass
import com.mrndevs.worldweather.ui.component.PressureIndicator
import com.mrndevs.worldweather.ui.component.ShimmerEffect
import com.mrndevs.worldweather.ui.screen.weather.WeatherUiState
import com.mrndevs.worldweather.ui.theme.SP14
import com.mrndevs.worldweather.ui.theme.SP18
import com.mrndevs.worldweather.ui.theme.W600
import com.mrndevs.worldweather.ui.theme.accordion
import com.mrndevs.worldweather.util.Constant
import com.mrndevs.worldweather.util.Util.addDegreeSymbol

@Composable
fun WeatherStatus(uiState: WeatherUiState, spacing: Dp = Constant.DEFAULT_SPACING.dp) {
    FlowRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing),
        maxItemsInEachRow = 2
    ) {
        UVContent(uv = uiState.weatherData.currentWeather.uv, isLoading = uiState.isLoading)
        ReelFeelContent(
            body = uiState.weatherData.currentWeather.text,
            value = uiState.weatherData.currentFeelsLike.addDegreeSymbol(),
            isLoading = uiState.isLoading
        )
        WindContent(
            wind = uiState.weatherData.currentWeather.windDir,
            degree = uiState.weatherData.currentWeather.windDegree,
            isLoading = uiState.isLoading
        )
        PressureContent(
            uiState = uiState,
            isLoading = uiState.isLoading
        )
    }
}

@Composable
private fun FlowRowScope.UVContent(uv: Int, isLoading: Boolean = false) {
    StatusItem(
        title = stringResource(R.string.title_uv),
        body = UltraVioletEnum.getUVIndex(uv).label,
        isLoading = isLoading
    ) {
        CircleBackground(modifier = Modifier.aspectRatio(1f)) {
            Text(
                text = uv.toString(),
                style = SP18.W600.copy(fontSize = 34.sp)
            )
        }
    }
}

@Composable
private fun FlowRowScope.ReelFeelContent(body: String, value: String, isLoading: Boolean = false) {
    StatusItem(
        title = stringResource(R.string.title_real_feel),
        body = body,
        isLoading = isLoading
    ) {
        CircleBackground(modifier = Modifier.aspectRatio(1f)) {
            Text(
                text = value,
                style = SP18.W600.copy(fontSize = 28.sp)
            )
        }
    }
}

@Composable
private fun FlowRowScope.WindContent(
    wind: WindDirectionEnum,
    degree: Int,
    isLoading: Boolean = false
) {
    StatusItem(
        title = stringResource(R.string.title_wind),
        body = wind.label,
        isLoading = isLoading
    ) {
        Compass(direction = degree.toFloat())
    }
}

@Composable
private fun FlowRowScope.PressureContent(
    uiState: WeatherUiState,
    isLoading: Boolean = false
) {
    uiState.settings?.let {
        val pressure = uiState.weatherData.currentPressure.toFloat()
        val pressureUnit = uiState.settings.pressureUnit

        StatusItem(
            title = stringResource(R.string.title_pressure),
            body = stringResource(
                R.string.placeholder_pressure,
                pressure.toInt(),
                pressureUnit.value
            ),
            isLoading = isLoading
        ) {
            PressureIndicator(pressure = pressure, pressureUnit = pressureUnit)
        }
    }
}

@Composable
private fun FlowRowScope.StatusItem(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    CardItem(modifier = modifier.weight(1f)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Constant.DEFAULT_SPACING.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    ShimmerEffect(width = 68.dp, height = 68.dp, shape = CircleShape)
                } else {
                    content()
                }
            }
            Text(text = title, style = SP14, color = accordion)
            if (isLoading) {
                ShimmerEffect(width = 50.dp, height = 14.dp)
            } else {
                Text(
                    text = body,
                    style = SP14.W600,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}