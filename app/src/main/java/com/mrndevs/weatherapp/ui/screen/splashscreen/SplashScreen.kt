package com.mrndevs.weatherapp.ui.screen.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.mrndevs.weatherapp.util.Util.getColorGradient
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToWeather: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getColorGradient())
    )
    LaunchedEffect(Unit) {
        delay(500)
        onNavigateToWeather()
    }
}