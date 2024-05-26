package com.mrndevs.weatherapp.ui.screen.weather.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.ui.component.CardItem
import com.mrndevs.weatherapp.ui.component.CircleBackground
import com.mrndevs.weatherapp.ui.component.ShimmerEffect
import com.mrndevs.weatherapp.ui.component.TemperatureIndicator
import com.mrndevs.weatherapp.ui.screen.weather.WeatherUiState
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherIcon
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherImage
import com.mrndevs.weatherapp.ui.theme.SP18
import com.mrndevs.weatherapp.ui.theme.SP20
import com.mrndevs.weatherapp.util.Constant
import com.mrndevs.weatherapp.util.Util.getFormattedDay

@Composable
fun WeatherForecast(uiState: WeatherUiState, spacing: Dp = Constant.DEFAULT_SPACING.dp) {
    CardItem(containerColor = MaterialTheme.colorScheme.primary) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(spacing))
            Row {
                Text(
                    text = stringResource(R.string.title_next_forecast),
                    style = SP20,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                WeatherIcon(painterRes = R.drawable.ic_calendar_event_line_24)
            }
            Spacer(modifier = Modifier.height(spacing))
            val forecast = uiState.forecast.forecastDay
            val minTemp = forecast.minByOrNull { it.day.minTempC }?.day?.minTempC ?: 0.0
            val maxTemp = forecast.maxByOrNull { it.day.maxTempC }?.day?.maxTempC ?: 0.0
            val globalTemp = minTemp to maxTemp
            if (uiState.isLoading) {
                repeat(3) {
                    ItemLoadingForecast()
                    Spacer(modifier = Modifier.height(spacing))
                }
            } else {
                repeat(forecast.size) { index ->
                    ItemForecast(forecast[index], globalTemp)
                    Spacer(modifier = Modifier.height(spacing))
                }
            }
        }
    }
}

@Composable
private fun ItemForecast(
    item: WeatherEntity.Forecast.ForecastDay,
    globalTemp: Pair<Double, Double>
) {
    val (globalMinTemp, globalMaxTemp) = globalTemp

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.dateEpoch.getFormattedDay(),
            style = SP18,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        CircleBackground {
            WeatherImage(
                code = item.day.code,
                isDay = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        TemperatureIndicator(
            modifier = Modifier.weight(1f),
            minTemp = item.day.minTempC,
            maxTemp = item.day.maxTempC,
            globalMinTemp = globalMinTemp,
            globalMaxTemp = globalMaxTemp
        )
    }
}

@Composable
private fun ItemLoadingForecast() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerEffect(width = 100.dp)
        Spacer(modifier = Modifier.weight(1f))
        ShimmerEffect(width = 32.dp, height = 32.dp, shape = CircleShape)
        Spacer(modifier = Modifier.weight(1f))
        ShimmerEffect(modifier = Modifier.weight(1f), width = 150.dp)
    }
}