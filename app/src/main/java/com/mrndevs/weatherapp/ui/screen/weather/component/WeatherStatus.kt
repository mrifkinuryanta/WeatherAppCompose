package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.ui.theme.SP14
import com.mrndevs.weatherapp.ui.theme.SP18
import com.mrndevs.weatherapp.ui.theme.SP20
import com.mrndevs.weatherapp.ui.theme.W600
import com.mrndevs.weatherapp.ui.theme.W700
import com.mrndevs.weatherapp.ui.theme.grey

@Composable
fun WeatherStatus(containerColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        WeatherImage(
            image = "",
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "28°",
            style = SP20.copy(fontSize = 64.sp).W600,
            color = Color.White
        )
        Text(text = buildAnnotatedString {
            withStyle(style = SP20.copy(Color.White).toSpanStyle()) {
                append("31°")
            }
            withStyle(style = SP18.copy(color = grey).toSpanStyle()) {
                append("/")
            }
            withStyle(style = SP20.copy(Color.White).toSpanStyle()) {
                append("25°")
            }
        })
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ItemStatus(painterRes = R.drawable.ic_rainy_line_24, text = "6%")
                ItemStatus(painterRes = R.drawable.ic_water_percent_line_24, text = "90%")
                ItemStatus(painterRes = R.drawable.ic_windy_line_24, text = "19 km/h")
            }
        }
    }
}

@Composable
private fun ItemStatus(
    painterRes: Int,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        WeatherIcon(painterRes = painterRes)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, style = SP14.W700, color = Color.White)
    }
}