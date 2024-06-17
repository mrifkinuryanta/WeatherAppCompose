package com.mrndevs.weatherapp.navigation

sealed class NavigationRoute(val route: String) {
    data object SplashScreen : NavigationRoute("splash_screen")
    data object WeatherPage : NavigationRoute("weather_page")
    data object WeatherSettingPage : NavigationRoute("weather_setting_page")
}