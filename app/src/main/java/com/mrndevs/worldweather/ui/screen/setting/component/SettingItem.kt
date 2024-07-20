package com.mrndevs.worldweather.ui.screen.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.ui.theme.SP14
import com.mrndevs.worldweather.ui.theme.W400
import com.mrndevs.worldweather.ui.theme.accordion
import com.mrndevs.worldweather.util.Constant

@Composable
fun SettingItem(
    icon: Int,
    title: String,
    description: String,
    trailingIcon: Int? = null,
    onClick: () -> Unit
) {
    val spacing = Constant.DEFAULT_SPACING
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = spacing.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(spacing.dp))
        Column {
            Text(text = title, style = SP14, color = Color.White)
            Text(text = description, style = SP14.W400, color = accordion)
        }
        Spacer(modifier = Modifier.weight(1f))
        trailingIcon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}