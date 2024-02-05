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
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherCitiesBottomSheet
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherForecast
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherHeader
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherStatus
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherToday
import com.mrndevs.weatherapp.ui.theme.backgroundLightGradient
import com.tagsamurai.tscomponents.pullrefresh.PullRefresh

@Composable
fun WeatherScreen() {
    var showCitiesBottomSheet by remember { mutableStateOf(false) }

    Column(
        Modifier
            .background(
                Brush.linearGradient(backgroundLightGradient)
            )
            .padding(horizontal = 30.dp)
    ) {
        WeatherHeader(cities = "Indonesia", onSelectCities = { showCitiesBottomSheet = true })

        PullRefresh(onRefresh = { /*TODO*/ }) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(bottom = 18.dp)
            ) {
                item {
                    WeatherStatus()
                }

                item {
                    WeatherToday()
                }

                item {
                    WeatherForecast()
                }
            }
        }

        if (showCitiesBottomSheet) {
            WeatherCitiesBottomSheet(onDismissRequest = { showCitiesBottomSheet = it })
        }
    }
}