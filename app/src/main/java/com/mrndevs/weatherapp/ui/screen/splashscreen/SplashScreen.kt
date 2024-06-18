package com.mrndevs.weatherapp.ui.screen.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.mrndevs.weatherapp.ui.theme.LocalTheme
import com.mrndevs.weatherapp.ui.theme.backgroundDarkGradient
import com.mrndevs.weatherapp.ui.theme.backgroundLightGradient
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToWeather: () -> Unit
) {
    val isDark = LocalTheme.current.isDarkTheme
    val gradient: Brush = if (isDark) {
        Brush.linearGradient(backgroundDarkGradient)
    } else {
        Brush.linearGradient(backgroundLightGradient)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    )
    LaunchedEffect(Unit) {
        delay(500)
        onNavigateToWeather()
    }
}