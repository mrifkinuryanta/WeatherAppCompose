package com.mrndevs.weatherapp.ui.screen.setting.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.theme.SP14
import com.mrndevs.weatherapp.ui.theme.W600

@Composable
fun SettingTitle(title: String) {
    Box(
        modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 14.dp, bottom = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, style = SP14.W600, color = Color.White)
    }
}