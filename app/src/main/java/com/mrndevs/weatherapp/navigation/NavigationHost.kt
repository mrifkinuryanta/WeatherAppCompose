package com.mrndevs.weatherapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrndevs.weatherapp.ui.screen.setting.SettingScreen
import com.mrndevs.weatherapp.ui.screen.weather.WeatherScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.WeatherPage.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        composable(route = NavigationRoute.WeatherPage.route) {
            WeatherScreen(onNavigateToSetting = { navController.navigate(NavigationRoute.WeatherSettingPage.route) })
        }
        composable(
            route = NavigationRoute.WeatherSettingPage.route,
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            SettingScreen(onNavigateUp = { navController.navigateUp() })
        }
    }
}