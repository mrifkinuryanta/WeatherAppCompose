package com.mrndevs.weatherapp.ui.screen.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrndevs.weatherapp.ui.component.pullrefresh.PullRefresh
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherLocationBottomSheet
import com.mrndevs.weatherapp.ui.screen.weather.model.WeatherScreenCallback
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherAppBar
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherForecast
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherHeader
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherStatus
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherToday
import com.mrndevs.weatherapp.ui.theme.LocalTheme
import com.mrndevs.weatherapp.ui.theme.backgroundDarkGradient
import com.mrndevs.weatherapp.ui.theme.backgroundLightGradient
import com.mrndevs.weatherapp.util.Constant

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    onNavigateToSetting: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val screenCallback = WeatherScreenCallback(
        onNavigateToSetting = onNavigateToSetting,
        onSearch = viewModel::updateSearchQuery,
        onGetWeather = viewModel::getWeather,
        onResetSearchData = viewModel::resetSearchData,
        onShowLocationSheet = viewModel::showLocationSheet,
        onRefresh = viewModel::refreshWeather
    )

    WeatherScreen(
        uiState = uiState.value,
        screenCallback = screenCallback
    )
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    screenCallback: WeatherScreenCallback
) {
    val isDarkTheme = LocalTheme.current.isDarkTheme
    val spacing = Constant.DEFAULT_SPACING.dp

    val gradient: Brush = if (isDarkTheme) {
        Brush.linearGradient(backgroundDarkGradient)
    } else {
        Brush.linearGradient(backgroundLightGradient)
    }

    Column(
        modifier = Modifier
            .background(gradient)
            .navigationBarsPadding()
    ) {
        WeatherAppBar(
            modifier = Modifier.padding(horizontal = 18.dp),
            location = uiState.location.name,
            onSelectLocation = { screenCallback.onShowLocationSheet(true) },
            onClickSetting = screenCallback.onNavigateToSetting
        )

        PullRefresh(onRefresh = screenCallback.onRefresh) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacing),
                contentPadding = PaddingValues(18.dp)
            ) {
                item {
                    WeatherHeader(uiState = uiState)
                }

                item {
                    WeatherToday(uiState = uiState)
                }

                item {
                    WeatherStatus(uiState = uiState)
                }

                item {
                    WeatherForecast(uiState = uiState)
                }
            }
        }

        WeatherLocationBottomSheet(
            onDismissRequest = { state -> screenCallback.onShowLocationSheet(state) },
            uiState = uiState,
            isShowSheet = uiState.isShowLocationSheet,
            onSearch = screenCallback.onSearch,
            onClick = screenCallback.onGetWeather,
            onResetSearchData = screenCallback.onResetSearchData
        )
    }
}