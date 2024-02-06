package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.theme.SP18
import com.mrndevs.weatherapp.ui.theme.SP20
import com.mrndevs.weatherapp.ui.theme.W400
import com.mrndevs.weatherapp.ui.theme.W600
import com.mrndevs.weatherapp.ui.theme.borderLightGradient
import com.mrndevs.weatherapp.ui.theme.borderNightGradient
import com.mrndevs.weatherapp.ui.theme.onBackgroundBorderLight
import com.mrndevs.weatherapp.ui.theme.onBackgroundBorderNight

@Composable
fun WeatherToday(containerColor: Color, isDarkTheme: Boolean) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Today",
                    style = SP20.W600,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Mar,9",
                    style = SP18.W400,
                    color = Color.White
                )
            }
            LazyRow(contentPadding = PaddingValues(horizontal = 18.dp, vertical = 16.dp)) {
                item { ItemToday(isSelected = true, isDarkTheme = isDarkTheme) }
                items(count = 6) {
                    ItemToday(isDarkTheme = isDarkTheme)
                }
            }
        }
    }
}

@Composable
private fun ItemToday(isSelected: Boolean = false, isDarkTheme: Boolean) {
    val border: Brush
    val onBorder: Color
    if (isDarkTheme) {
        border = Brush.radialGradient(borderNightGradient)
        onBorder = onBackgroundBorderNight
    } else {
        border = Brush.radialGradient(borderLightGradient)
        onBorder = onBackgroundBorderLight
    }

    Column(
        modifier =
        if (isSelected) {
            Modifier
                .background(color = onBorder, shape = RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(20.dp),
                    brush = border
                )
                .padding(horizontal = 15.dp, vertical = 13.dp)
        } else Modifier.padding(horizontal = 15.dp, vertical = 13.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "29°C",
            style = SP18.W400,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(30.dp))
        WeatherImage(image = "")
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "15.00",
            style = SP18.W400,
            color = Color.White
        )
    }
}