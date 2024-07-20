package com.mrndevs.worldweather.ui.screen.weather.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.R
import com.mrndevs.worldweather.ui.screen.weather.component.WeatherIcon
import com.mrndevs.worldweather.ui.theme.SP18

@Composable
fun worldweatherBar(
    modifier: Modifier = Modifier,
    location: String?,
    onSelectLocation: () -> Unit,
    onClickSetting: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .clickable { onSelectLocation() }) {
            WeatherIcon(painterRes = R.drawable.ic_map_pin_2_line_24, size = 27.dp)
            Spacer(modifier = Modifier.size(12.dp))
            location?.let {
                Text(
                    text = location.ifBlank { "Location" },
                    style = SP18,
                    color = Color.White
                )
            }
            WeatherIcon(painterRes = R.drawable.ic_arrow_down_24)
        }
        Spacer(modifier = Modifier.weight(1f))
        WeatherIcon(
            painterRes = R.drawable.ic_settings_3_line_24,
            size = 27.dp,
            onClick = {
                if (!location.isNullOrBlank()) onClickSetting()
            }
        )
    }
}