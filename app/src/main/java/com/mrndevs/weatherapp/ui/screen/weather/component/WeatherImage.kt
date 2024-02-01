package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.mrndevs.weatherapp.R

@Composable
fun WeatherImage(image: Any, modifier: Modifier = Modifier) {
    AsyncImage(
        model = image,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.ic_cloud_24),
        error = painterResource(id = R.drawable.ic_cloud_24),
        modifier = modifier
    )
}