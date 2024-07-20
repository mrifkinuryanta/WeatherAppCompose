package com.mrndevs.worldweather.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    border: Brush? = null,
    content: @Composable () -> Unit
) {
    val modifierBorder = if (border != null) {
        modifier.border(
            width = 1.5.dp,
            brush = border,
            shape = RoundedCornerShape(20.dp)
        )
    } else modifier

    Card(
        modifier = modifierBorder,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        content()
    }
}

@Composable
fun CardItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color,
    border: Brush? = null,
    content: @Composable () -> Unit
) {
    val modifierBorder = if (border != null) {
        modifier.border(
            width = 1.5.dp,
            brush = border,
            shape = RoundedCornerShape(20.dp)
        )
    } else modifier

    Card(
        onClick = onClick,
        modifier = modifierBorder,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        content()
    }
}