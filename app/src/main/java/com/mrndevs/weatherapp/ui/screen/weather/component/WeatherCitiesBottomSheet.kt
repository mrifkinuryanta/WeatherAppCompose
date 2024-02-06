package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.component.BottomSheet
import com.mrndevs.weatherapp.ui.component.SearchFieldWithIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherCitiesBottomSheet(
    onDismissRequest: () -> Unit,
    isShowSheet: Boolean,
    isDarkTheme: Boolean
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var query by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (isShowSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    val containerColor = if (isDarkTheme) Color.White else Color.Black

    BottomSheet(
        onDismissRequest = {
            onDismissRequest()
            scope.launch { sheetState.hide() }
        },
        containerColor = containerColor,
        sheetState = sheetState,
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