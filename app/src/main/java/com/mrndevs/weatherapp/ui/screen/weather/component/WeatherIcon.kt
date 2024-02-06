package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherIcon(painterRes: Int, size: Dp = 24.dp) {
    Icon(
        painter = painterResource(painterRes),
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier.size(size)
    )
}

@Composable
fun WeatherIcon(painterRes: Int, size: Dp = 24.dp, onClick: (() -> Unit)? = null) {
    Icon(
        painter = painterResource(painterRes),
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier
            .size(size)
            .clickable { onClick?.invoke() }
    )
}