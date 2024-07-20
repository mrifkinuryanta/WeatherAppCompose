package com.mrndevs.worldweather.ui.screen.weather.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.mrndevs.worldweather.util.Util.getAssetPrefix

@Composable
fun WeatherImage(code: Int, isDay: Boolean, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(model = code.getAssetPrefix(isDay))
    val state = painter.state

    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f,
        label = "WeatherImageTransition"
    )

    Image(
        painter = painter,
        contentDescription = "Weather Image",
        modifier = modifier.alpha(transition)
    )
}