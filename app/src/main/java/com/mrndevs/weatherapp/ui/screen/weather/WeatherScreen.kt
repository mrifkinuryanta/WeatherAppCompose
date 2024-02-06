package com.mrndevs.weatherapp.ui.screen.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherCitiesBottomSheet
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherForecast
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherHeader
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherStatus
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherToday
import com.mrndevs.weatherapp.ui.theme.backgroundLightGradient
import com.mrndevs.weatherapp.ui.theme.backgroundNightGradient
import com.mrndevs.weatherapp.ui.theme.onBackgroundLight
import com.mrndevs.weatherapp.ui.theme.onBackgroundNight
import com.tagsamurai.tscomponents.pullrefresh.PullRefresh

@Composable
fun WeatherScreen() {
    var showCitiesBottomSheet by remember { mutableStateOf(false) }
    var isDarkTheme by remember { mutableStateOf(false) }

    val gradient: Brush
    val containerColor: Color
    if (isDarkTheme) {
        gradient = Brush.linearGradient(backgroundNightGradient)
        containerColor = onBackgroundNight
    } else {
        gradient = Brush.linearGradient(backgroundLightGradient)
        containerColor = onBackgroundLight
    }

    Column(
        Modifier
            .background(gradient)
            .padding(horizontal = 30.dp)
    ) {
        WeatherHeader(
            cities = "Indonesia",
            onSelectCities = { showCitiesBottomSheet = true },
            onClickNotification = { isDarkTheme = !isDarkTheme }
        )

        PullRefresh(onRefresh = { /*TODO*/ }) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(bottom = 18.dp)
            ) {
                item {
                    WeatherStatus(containerColor)
                }

                item {
                    WeatherToday(containerColor, isDarkTheme)
                }

                item {
                    WeatherForecast(containerColor)
                }
            }
        }

        WeatherCitiesBottomSheet(
            onDismissRequest = { showCitiesBottomSheet = false },
            isShowSheet = showCitiesBottomSheet,
            isDarkTheme = isDarkTheme
        )
    }
}