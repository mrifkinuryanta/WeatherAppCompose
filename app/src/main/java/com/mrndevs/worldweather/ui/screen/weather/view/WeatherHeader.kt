package com.mrndevs.worldweather.ui.screen.weather.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.mrndevs.worldweather.R
import com.mrndevs.worldweather.ui.component.ShimmerEffect
import com.mrndevs.worldweather.ui.screen.weather.WeatherUiState
import com.mrndevs.worldweather.ui.screen.weather.component.WeatherIcon
import com.mrndevs.worldweather.ui.screen.weather.component.WeatherImage
import com.mrndevs.worldweather.ui.screen.weather.component.WeatherTempMinMax
import com.mrndevs.worldweather.ui.theme.SP14
import com.mrndevs.worldweather.ui.theme.SP20
import com.mrndevs.worldweather.ui.theme.W600
import com.mrndevs.worldweather.util.Constant
import com.mrndevs.worldweather.util.Util.addDegreeSymbol

@Composable
fun WeatherHeader(uiState: WeatherUiState, spacing: Dp = Constant.DEFAULT_SPACING.dp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        uiState.apply {
            Box(modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center) {
                WeatherImage(
                    code = uiState.weatherData.currentWeather.code,
                    isDay = uiState.weatherData.currentWeather.isDay,
                    modifier = Modifier.fillMaxSize()
                )
            }
            if (isLoading) {
                Spacer(modifier = Modifier.height(spacing))
                ShimmerEffect(width = 64.dp, height = 64.dp, shape = CircleShape)
                Spacer(modifier = Modifier.height(spacing))
            } else {
                Text(
                    text = weatherData.currentTemp.addDegreeSymbol(),
                    style = SP20.copy(fontSize = 64.sp),
                    color = Color.White
                )
            }
            WeatherTempMinMax(weatherData, isLoading)
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ItemStatus(
                        painterRes = R.drawable.ic_rainy_line_24,
                        text = stringResource(
                            R.string.placeholder_cloud,
                            uiState.weatherData.currentWeather.cloud
                        ),
                        isLoading = isLoading
                    )
                    ItemStatus(
                        painterRes = R.drawable.ic_water_percent_line_24,
                        text = stringResource(
                            R.string.placeholder_humidity,
                            weatherData.currentWeather.humidity
                        ),
                        isLoading = isLoading
                    )
                    settings?.let {
                        ItemStatus(
                            painterRes = R.drawable.ic_windy_line_24,
                            text = stringResource(
                                R.string.placeholder_wind,
                                weatherData.currentWindSpeed.toInt(),
                                settings.windSpeedUnit.value
                            ),
                            isLoading = isLoading
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemStatus(
    painterRes: Int,
    text: String,
    isLoading: Boolean = false
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        WeatherIcon(painterRes = painterRes)
        Spacer(modifier = Modifier.width(4.dp))
        if (isLoading) {
            ShimmerEffect(width = 50.dp, height = 14.dp)
        } else {
            Text(text = text, style = SP14.W600, color = Color.White)
        }
    }
}