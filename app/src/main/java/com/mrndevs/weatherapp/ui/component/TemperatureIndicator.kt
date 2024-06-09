package com.mrndevs.weatherapp.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.data.source.local.model.TempUnitEnum
import com.mrndevs.weatherapp.ui.theme.SP16
import com.mrndevs.weatherapp.ui.theme.W400
import com.mrndevs.weatherapp.ui.theme.orange
import com.mrndevs.weatherapp.util.Util.addDegreeSymbol
import kotlin.math.abs

@Composable
fun TemperatureIndicator(
    modifier: Modifier = Modifier,
    tempUnit: TempUnitEnum,
    minTemp: Double,
    maxTemp: Double,
    globalMinTemp: Double,
    globalMaxTemp: Double
) {
    val isCelsius = tempUnit == TempUnitEnum.CELSIUS
    val minPossibleTemp = if (isCelsius) -50.0 else -58.0
    val adjustedMinTemp = minTemp + abs(minPossibleTemp)
    val progress = adjustedMinTemp / maxTemp
    val paddingStart = if (minTemp == globalMinTemp) 0.dp else (minTemp - globalMinTemp).dp
    val paddingEnd = if (maxTemp == globalMaxTemp) 0.dp else (globalMaxTemp - maxTemp).dp

    val colors: List<Color> = when {
        minTemp < if (isCelsius) 0 else 32 -> listOf(Color.Blue, Color.Cyan, Color.Green)
        minTemp in if (isCelsius) 0.0..15.0 else 32.0..59.0 -> listOf(
            Color.Cyan,
            Color.Green,
            Color.Yellow
        )

        minTemp in if (isCelsius) 15.0..30.0 else 59.0..86.0 -> listOf(
            Color.Green,
            Color.Yellow,
            orange
        )

        else -> listOf(Color.Yellow, orange, Color.Red)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = minTemp.addDegreeSymbol(),
            style = SP16.W400,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        CustomProgressIndicator(
            modifier = Modifier.weight(1.5f),
            progress = progress.toFloat(),
            padding = PaddingValues(start = paddingStart, end = paddingEnd),
            defaultProgressIndicator = DefaultProgressIndicator().copy(
                color = Brush.linearGradient(colors),
                trackColor = MaterialTheme.colorScheme.background
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = maxTemp.addDegreeSymbol(),
            style = SP16.W400,
            color = Color.White
        )
    }
}

@Composable
private fun CustomProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    padding: PaddingValues,
    defaultProgressIndicator: DefaultProgressIndicator = DefaultProgressIndicator()
) {
    val indicatorSize: Dp = 8.dp

    val animatedProgress = animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    ).value

    Box(modifier = modifier) {
        // Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(indicatorSize)
                .background(color = defaultProgressIndicator.trackColor, shape = CircleShape)
        )
        // Indicator
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(animatedProgress)
                .requiredHeight(indicatorSize)
                .background(brush = defaultProgressIndicator.color, shape = CircleShape)
        )
    }
}

data class DefaultProgressIndicator(
    val color: Brush = SolidColor(Color.White),
    val trackColor: Color = Color.Gray
)