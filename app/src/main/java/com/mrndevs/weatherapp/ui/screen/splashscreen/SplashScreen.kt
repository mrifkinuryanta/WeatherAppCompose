package com.mrndevs.weatherapp.ui.screen.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mrndevs.weatherapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToWeather: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Splash Logo"
        )
    }
    LaunchedEffect(Unit) {
        delay(1000)
        onNavigateToWeather()
    }
}