package com.mrndevs.weatherapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.data.source.local.model.EmptyStatusEnum
import com.mrndevs.weatherapp.ui.theme.SP16

@Composable
fun EmptyState(modifier: Modifier = Modifier, status: EmptyStatusEnum) {
    val icon: Int
    val placeholder: String

    when (status) {
        EmptyStatusEnum.SEARCH -> {
            icon = R.drawable.ic_search_eye_line_24
            placeholder = "Search for a city"
        }

        EmptyStatusEnum.EMPTY_SEARCH -> {
            icon = R.drawable.ic_cloud_off_line_24
            placeholder = "No search results"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.heightIn(8.dp))
        Text(text = placeholder, color = Color.White, style = SP16)
    }
}