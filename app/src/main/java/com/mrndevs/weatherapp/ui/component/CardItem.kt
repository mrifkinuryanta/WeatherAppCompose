package com.mrndevs.weatherapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tagsamurai.tscomponents.theme.outline

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    content: @Composable () -> Unit
) {
    CardItemContent(modifier = modifier, containerColor = containerColor, content = content)
}

@Composable
internal fun CardItemContent(
    modifier: Modifier,
    containerColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = outline),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        )
    ) {
        content()
    }
}