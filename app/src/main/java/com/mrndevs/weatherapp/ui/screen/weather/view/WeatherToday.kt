package com.mrndevs.weatherapp.ui.screen.weather.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.data.source.local.model.WeatherEntity
import com.mrndevs.weatherapp.ui.component.CardItem
import com.mrndevs.weatherapp.ui.component.CircleBackground
import com.mrndevs.weatherapp.ui.component.ShimmerEffect
import com.mrndevs.weatherapp.ui.screen.weather.WeatherUiState
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherImage
import com.mrndevs.weatherapp.ui.theme.SP18
import com.mrndevs.weatherapp.ui.theme.SP20
import com.mrndevs.weatherapp.ui.theme.W400
import com.mrndevs.weatherapp.ui.theme.borderDarkGradient
import com.mrndevs.weatherapp.ui.theme.borderLightGradient
import com.mrndevs.weatherapp.ui.theme.secondaryDark
import com.mrndevs.weatherapp.ui.theme.secondaryLight
import com.mrndevs.weatherapp.util.Constant
import com.mrndevs.weatherapp.util.Util.addDegreeSymbol
import com.mrndevs.weatherapp.util.Util.getFormattedDate
import com.mrndevs.weatherapp.util.Util.getFormattedTime

@Composable
fun WeatherToday(uiState: WeatherUiState, spacing: Dp = Constant.DEFAULT_SPACING.dp) {
    CardItem(containerColor = MaterialTheme.colorScheme.primary) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .padding(top = spacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.title_today),
                    style = SP20,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                if (uiState.isLoading) {
                    ShimmerEffect(width = 80.dp)
                } else {
                    val date = uiState.forecast.forecastDay.firstOrNull()?.dateEpoch ?: 0
                    Text(
                        text = if (date != Constant.ZERO) date.getFormattedDate() else "",
                        style = SP18.W400,
                        color = Color.White
                    )
                }
            }
            val list = uiState.forecast.forecastDay.firstOrNull()?.hour ?: emptyList()
            LazyRow(
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = spacing),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (uiState.isLoading) {
                    items(5) { index ->
                        ItemLoadingToday(index == 0)
                    }
                }
                items(list.size) { index ->
                    ItemToday(list[index], index == 0)
                }
            }
        }
    }
}

@Composable
private fun ItemToday(item: WeatherEntity.Forecast.ForecastDay.Hour, isSelected: Boolean = false) {
    val isDarkTheme = isSystemInDarkTheme()
    val paddingValues = PaddingValues(Constant.DEFAULT_SPACING.dp)
    val border: Brush
    val onBorder: Color
    if (isDarkTheme) {
        border = Brush.radialGradient(borderDarkGradient)
        onBorder = secondaryDark
    } else {
        border = Brush.radialGradient(borderLightGradient)
        onBorder = secondaryLight
    }

    val modifier =
        if (isSelected) {
            Modifier
                .background(color = onBorder, shape = RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(20.dp),
                    brush = border
                )
        } else Modifier

    Column(
        modifier = modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.tempC.addDegreeSymbol(),
            style = SP18.W400,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        CircleBackground {
            WeatherImage(
                code = item.code,
                isDay = item.isDay,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        val time =
            if (isSelected) stringResource(R.string.title_now) else item.timeEpoch.getFormattedTime()
        Text(
            text = time,
            style = SP18.W400,
            color = Color.White
        )
    }
}

@Composable
private fun ItemLoadingToday(isSelected: Boolean = false) {
    val isDarkTheme = isSystemInDarkTheme()
    val paddingValues = PaddingValues(Constant.DEFAULT_SPACING.dp)
    val border: Brush
    val onBorder: Color
    if (isDarkTheme) {
        border = Brush.radialGradient(borderDarkGradient)
        onBorder = secondaryDark
    } else {
        border = Brush.radialGradient(borderLightGradient)
        onBorder = secondaryLight
    }

    val modifier =
        if (isSelected) {
            Modifier
                .background(color = onBorder, shape = RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(20.dp),
                    brush = border
                )
        } else Modifier

    Column(
        modifier = modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShimmerEffect(width = 40.dp)
        Spacer(modifier = Modifier.height(24.dp))
        ShimmerEffect(width = 32.dp, height = 32.dp, shape = CircleShape)
        Spacer(modifier = Modifier.height(24.dp))
        ShimmerEffect(width = 50.dp)
    }
}