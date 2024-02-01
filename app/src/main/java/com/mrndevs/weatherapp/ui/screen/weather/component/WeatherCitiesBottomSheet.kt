package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.component.BottomSheet
import com.mrndevs.weatherapp.ui.component.SearchFieldWithIndicator
import com.mrndevs.weatherapp.ui.theme.backgroundNight

@Composable
fun WeatherCitiesBottomSheet(onDismissRequest: (Boolean) -> Unit) {
    var query by remember { mutableStateOf("") }

    BottomSheet(
        onDismissRequest = onDismissRequest,
        skipPartiallyExpanded = true,
        containerColor = backgroundNight,
        modifier = Modifier.fillMaxHeight(0.8f)
    ) {
        Column(modifier = Modifier.padding(horizontal = 40.dp)) {
            SearchFieldWithIndicator(
                onRemoveQuery = { query = "" },
                onSearchConfirm = { query = it })
            Spacer(modifier = Modifier.size(12.dp))
            LazyColumn {

            }
        }
    }
}