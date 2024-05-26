@file:OptIn(ExperimentalLayoutApi::class)

package com.mrndevs.weatherapp.ui.screen.weather.view

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.data.source.local.model.UltraVioletEnum
import com.mrndevs.weatherapp.data.source.local.model.WindDirectionEnum
import com.mrndevs.weatherapp.ui.component.CardItem
import com.mrndevs.weatherapp.ui.component.CircleBackground
import com.mrndevs.weatherapp.ui.component.Compass
import com.mrndevs.weatherapp.ui.component.PressureIndicator
import com.mrndevs.weatherapp.ui.component.ShimmerEffect
import com.mrndevs.weatherapp.ui.screen.weather.WeatherUiState
import com.mrndevs.weatherapp.ui.theme.SP14
import com.mrndevs.weatherapp.ui.theme.SP18
import com.mrndevs.weatherapp.ui.theme.W600
import com.mrndevs.weatherapp.ui.theme.grey
import com.mrndevs.weatherapp.util.Constant
import com.mrndevs.weatherapp.util.Util.addDegreeSymbol

@Composable
fun WeatherStatus(uiState: WeatherUiState, spacing: Dp = Constant.DEFAULT_SPACING.dp) {
    FlowRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing),
        maxItemsInEachRow = 2
    ) {
        UVContent(uv = uiState.currentWeather.uv, isLoading = uiState.isLoading)
        ReelFeelContent(
            body = uiState.currentWeather.text,
            value = uiState.currentWeather.feelsLikeC.addDegreeSymbol(),
            isLoading = uiState.isLoading
        )
        WindContent(
            wind = uiState.currentWeather.windDir,
            degree = uiState.currentWeather.windDegree,
            isLoading = uiState.isLoading
        )
        PressureContent(
            pressure = uiState.currentWeather.pressureMb.toFloat(),
            isLoading = uiState.isLoading
        )
    }
}

@Composable
private fun FlowRowScope.UVContent(uv: Int, isLoading: Boolean = false) {
    StatusItem(
        modifier = Modifier.weight(1f),
        title = stringResource(R.string.title_uv),
        body = UltraVioletEnum.getUVIndex(uv).label,
        containerColor = MaterialTheme.colorScheme.primary,
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
        modifier = Modifier.weight(1f),
        title = stringResource(R.string.title_real_feel),
        body = body,
        containerColor = MaterialTheme.colorScheme.primary,
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
        modifier = Modifier.weight(1f),
        title = stringResource(R.string.title_wind),
        body = wind.label,
        containerColor = MaterialTheme.colorScheme.primary,
        isLoading = isLoading
    ) {
        Compass(direction = degree.toFloat())
    }
}

@Composable
private fun FlowRowScope.PressureContent(pressure: Float, isLoading: Boolean = false) {
    StatusItem(
        modifier = Modifier.weight(1f),
        title = stringResource(R.string.title_pressure),
        body = stringResource(R.string.placeholder_hpa, pressure.toInt()),
        containerColor = MaterialTheme.colorScheme.primary,
        isLoading = isLoading
    ) {
        PressureIndicator(pressure = pressure)
    }
}

@Composable
private fun StatusItem(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    containerColor: Color,
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    CardItem(modifier = modifier, containerColor = containerColor) {
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
            Text(text = title, style = SP14, color = grey)
            if (isLoading) {
                ShimmerEffect(width = 50.dp, height = 14.dp)
            } else {
                Text(text = body, style = SP14.W600, color = Color.White)
            }
        }
    }
}