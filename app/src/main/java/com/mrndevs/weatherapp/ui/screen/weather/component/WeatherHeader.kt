package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.ui.theme.SP18

@Composable
fun WeatherHeader(cities: String, onSelectCities: () -> Unit, onClickNotification: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .clickable { onSelectCities() }) {
            WeatherIcon(painterRes = R.drawable.ic_map_pin_2_line_24, size = 27.dp)
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = cities,
                style = SP18,
                color = Color.White
            )
            WeatherIcon(painterRes = R.drawable.ic_arrow_down_24)
        }
        Spacer(modifier = Modifier.weight(1f))
        WeatherIcon(
            painterRes = R.drawable.ic_notification_line_24,
            size = 27.dp,
            onClick = onClickNotification
        )
    }
}