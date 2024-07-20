package com.mrndevs.worldweather.ui.screen.setting.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.R
import com.mrndevs.worldweather.ui.component.CardItem
import com.mrndevs.worldweather.ui.screen.setting.component.SettingItem
import com.mrndevs.worldweather.ui.screen.setting.component.SettingTitle
import com.mrndevs.worldweather.util.Util

@Composable
fun SettingFeedback() {
    val context = LocalContext.current
    CardItem(modifier = Modifier.padding(horizontal = 18.dp)) {
        Column {
            SettingTitle(title = stringResource(R.string.title_feedback))
            SettingItem(
                icon = R.drawable.ic_star_line_24,
                title = stringResource(R.string.title_rate_us),
                description = stringResource(R.string.placeholder_rate_us_on_play_store),
                trailingIcon = R.drawable.ic_share_box_line_24,
                onClick = { Util.openPlayStore(context) }
            )
        }
    }
}