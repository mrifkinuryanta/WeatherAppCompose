package com.mrndevs.worldweather.ui.component

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp = 16.dp,
    borderRadius: Dp = 8.dp,
    shape: Shape? = null
) {
    val colors = listOf(
        Color.White.copy(alpha = 0.6f),
        Color.White.copy(alpha = 0.2f),
        Color.White.copy(alpha = 0.6f)
    )
    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val animation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutLinearInEasing),
            RepeatMode.Restart
        ),
        label = "shimmerAnimation"
    )

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(shape ?: RoundedCornerShape(borderRadius))
            .background(
                Brush.linearGradient(
                    colors = colors,
                    start = Offset(animation.value - 200f, animation.value - 200f),
                    end = Offset(animation.value, animation.value)
                )
            ),
        contentAlignment = Alignment.Center
    ) {}
}