package com.mrndevs.weatherapp.ui.screen.weather.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.util.Util.getAssetPrefix

@Composable
fun WeatherImage(code: Int, isDay: Boolean, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(
        model = code.getAssetPrefix(isDay),
        placeholder = painterResource(id = R.drawable.ic_cloud_24),
        error = painterResource(id = R.drawable.ic_cloud_24)
    )

    Image(painter = painter, contentDescription = null, modifier = modifier)
}