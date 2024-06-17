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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.data.source.local.model.TempUnitEnum
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
    CardItem {
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
            if (uiState.isLoading) {
                repeat(3) {
                    ItemLoadingForecast()
                    Spacer(modifier = Modifier.height(spacing))
                }
            } else {
                val items = uiState.weatherData.forecastDay
                repeat(items.size) { index ->
                    ItemForecast(
                        uiState = uiState,
                        item = items[index]
                    )
                    Spacer(modifier = Modifier.height(spacing))
                }
            }
        }
    }
}

@Composable
private fun ItemForecast(
    uiState: WeatherUiState,
    item: WeatherEntity.Forecast.ForecastDay
) {
    val (globalMinTemp, globalMaxTemp) = uiState.weatherData.forecastMinTemp to uiState.weatherData.forecastMaxTemp
    val (minTemp, maxTemp) = if (uiState.settings?.tempUnit == TempUnitEnum.CELSIUS) {
        item.day.minTempC to item.day.maxTempC
    } else {
        item.day.minTempF to item.day.maxTempF
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = item.dateEpoch.getFormattedDay(uiState.weatherData.currentLocation.tzId),
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
        uiState.settings?.let {
            TemperatureIndicator(
                modifier = Modifier.weight(1f),
                tempUnit = uiState.settings.tempUnit,
                minTemp = minTemp,
                maxTemp = maxTemp,
                globalMinTemp = globalMinTemp,
                globalMaxTemp = globalMaxTemp
            )
        }
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