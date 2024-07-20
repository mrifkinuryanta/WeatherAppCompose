package com.mrndevs.worldweather.ui.component

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.ui.theme.SP10
import com.mrndevs.worldweather.ui.theme.W600
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Compass(modifier: Modifier = Modifier, direction: Float) {
    CompassContent(
        modifier = modifier,
        direction = direction,
        background = MaterialTheme.colorScheme.background
    )
}

@Composable
private fun CompassContent(
    modifier: Modifier = Modifier,
    background: Color = Color.Gray,
    direction: Float
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = size / 2f
            val radius = size.minDimension / 2f
            val paint = Paint().apply {
                color = background
                style = PaintingStyle.Fill
                strokeWidth = 5f
            }

            // Draw the compass circle
            drawCircle(
                color = paint.color,
                radius = radius,
                center = Offset(center.width, center.height)
            )

            // Draw the direction indicator
            val indicatorLength = radius - 10f
            val indicatorAngle = Math.toRadians(direction.toDouble())

            val indicatorX = center.width + indicatorLength * cos(indicatorAngle).toFloat()
            val indicatorY = center.height + indicatorLength * sin(indicatorAngle).toFloat()

            drawLine(
                color = Color.Red,
                start = Offset(center.width, center.height),
                end = Offset(indicatorX, indicatorY),
                strokeWidth = 5f
            )

            // Draw the cardinal points
            val textRadius = radius + 40f
            val textPaint = android.graphics.Paint().apply {
                color = Color.White.toArgb()
                textSize = 48f
                typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
            }

            val angle = Math.toRadians(0.0)
            val textX = center.width + textRadius * cos(angle).toFloat() - textPaint.textSize / 2
            val textY = center.height - 5 + textPaint.textSize / 2

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    "N",
                    textX,
                    textY,
                    textPaint
                )
            }
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .size(35.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = direction.toString().split(".")[0] + "°",
                style = SP10.W600,
                color = Color.White
            )
        }
    }
}