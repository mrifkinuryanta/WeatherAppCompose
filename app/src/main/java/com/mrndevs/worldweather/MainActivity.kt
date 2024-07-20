package com.mrndevs.worldweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrndevs.worldweather.navigation.NavigationHost
import com.mrndevs.worldweather.ui.screen.weather.WeatherViewModel
import com.mrndevs.worldweather.ui.theme.LocalTheme
import com.mrndevs.worldweather.ui.theme.ThemeState
import com.mrndevs.worldweather.ui.theme.worldweatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.getSettings()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val themeState = ThemeState(isDarkTheme = uiState.settings?.isDarkTheme == true)

            val isDark = themeState.isDarkTheme
            val color: Int = if (isDark) R.color.primary_dark else R.color.primary_light
            LocalView.current.setBackgroundColor(LocalContext.current.getColor(color))

            CompositionLocalProvider(LocalTheme provides themeState) {
                worldweatherTheme {
                    NavigationHost()
                }
            }
        }
    }
}