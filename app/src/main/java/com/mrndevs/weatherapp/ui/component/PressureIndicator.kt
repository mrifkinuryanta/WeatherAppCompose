package com.mrndevs.weatherapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.R

@Composable
fun PressureIndicator(modifier: Modifier = Modifier, pressure: Float) {
    PressureIndicatorContent(modifier = modifier, pressure = pressure)
}

@Composable
private fun PressureIndicatorContent(
    modifier: Modifier = Modifier,
    pressure: Float,
    minPressure: Float = 950f,
    maxPressure: Float = 1050f
) {
    val normalizedPressure =
        ((pressure - minPressure) / (maxPressure - minPressure)).coerceIn(0f, 1f)
    val backgroundColor = MaterialTheme.colorScheme.background
    val fillColor: List<Color>
    val icon: Int

    when {
        normalizedPressure <= 0.33f -> {
            fillColor = listOf(Color(0xFF00FF00), Color(0xFFFFFF00))
            icon = R.drawable.ic_arrow_downward_24
        }

        normalizedPressure < 0.63f -> {
            fillColor = listOf(Color(0xFFFFFF00), Color(0xFFFFA500))
            icon = R.drawable.ic_arrow_downward_24
        }

        normalizedPressure >= 0.63f -> {
            fillColor = listOf(Color(0xFFFFA500), Color(0xFFFF0000))
            icon = R.drawable.ic_arrow_upward_24
        }

        else -> {
            fillColor = listOf(Color(0xFFFF0000), Color(0xFFEE82EE))
            icon = R.drawable.ic_arrow_upward_24
        }
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .background(backgroundColor, shape = CircleShape)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(backgroundColor).plus(fillColor),
                    center = center,
                    radius = size.minDimension / 2
                ),
                radius = size.minDimension / 2 * normalizedPressure,
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(35.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}