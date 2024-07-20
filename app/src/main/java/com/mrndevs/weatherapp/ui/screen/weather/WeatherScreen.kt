package com.mrndevs.weatherapp.ui.screen.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrndevs.weatherapp.data.source.local.model.EmptyStatusEnum
import com.mrndevs.weatherapp.ui.component.EmptyState
import com.mrndevs.weatherapp.ui.component.pullrefresh.PullRefresh
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherLocationDialog
import com.mrndevs.weatherapp.ui.screen.weather.model.WeatherScreenCallback
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherAppBar
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherForecast
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherHeader
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherStatus
import com.mrndevs.weatherapp.ui.screen.weather.view.WeatherToday
import com.mrndevs.weatherapp.util.Constant
import com.mrndevs.weatherapp.util.Util.getColorGradient

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    onNavigateToSetting: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(uiState.settings) {
        viewModel.init()
    }

    val screenCallback = WeatherScreenCallback(
        onNavigateToSetting = onNavigateToSetting,
        onSearch = viewModel::updateSearchQuery,
        onGetWeather = viewModel::getWeather,
        onResetSearchData = viewModel::resetSearchData,
        onShowLocationSheet = viewModel::showLocationSheet,
        onRefresh = viewModel::refreshWeather
    )

    WeatherScreen(
        uiState = uiState,
        screenCallback = screenCallback
    )
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    screenCallback: WeatherScreenCallback
) {
    val spacing = Constant.DEFAULT_SPACING.dp

    Column(
        modifier = Modifier
            .background(getColorGradient())
            .navigationBarsPadding()
    ) {
        WeatherAppBar(
            modifier = Modifier.padding(horizontal = 18.dp),
            location = uiState.settings?.currentLocation,
            onSelectLocation = { screenCallback.onShowLocationSheet(true) },
            onClickSetting = screenCallback.onNavigateToSetting
        )

        uiState.settings?.let {
            if (uiState.settings.isFirstRunApp) {
                Box(modifier = Modifier.fillMaxSize()) {
                    EmptyState(status = EmptyStatusEnum.FIRST_RUN_APP) {
                        screenCallback.onShowLocationSheet(true)
                    }
                }
            } else {
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
            }
        }

        WeatherLocationDialog(
            onDismissRequest = { state -> screenCallback.onShowLocationSheet(state) },
            uiState = uiState,
            isShowDialog = uiState.isShowLocationSheet,
            onSearch = screenCallback.onSearch,
            onClick = screenCallback.onGetWeather,
            onResetSearchData = screenCallback.onResetSearchData
        )
    }
}