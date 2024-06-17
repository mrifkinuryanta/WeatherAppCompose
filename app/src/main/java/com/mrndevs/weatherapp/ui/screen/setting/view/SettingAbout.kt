package com.mrndevs.weatherapp.ui.screen.setting.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.BuildConfig
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.ui.component.CardItem
import com.mrndevs.weatherapp.ui.screen.setting.component.SettingItem
import com.mrndevs.weatherapp.ui.screen.setting.component.SettingTitle
import com.mrndevs.weatherapp.util.Constant
import com.mrndevs.weatherapp.util.Util

@Composable
fun SettingAbout() {
    val context = LocalContext.current
    CardItem(modifier = Modifier.padding(horizontal = 18.dp)) {
        Column {
            SettingTitle(title = stringResource(R.string.title_about))
            SettingItem(
                icon = R.drawable.ic_smartphone_line_24,
                title = stringResource(R.string.title_version),
                description = BuildConfig.VERSION_NAME,
                onClick = {}
            )
            SettingItem(
                icon = R.drawable.ic_code_s_line_24,
                title = stringResource(R.string.title_source_code),
                description = stringResource(R.string.placeholder_available_on_github),
                trailingIcon = R.drawable.ic_share_box_line_24,
                onClick = { Util.openBrowser(context = context, url = Constant.LINK_SOURCE_CODE) }
            )
            SettingItem(
                icon = R.drawable.ic_lock_line_24,
                title = stringResource(R.string.title_privacy_policy),
                description = stringResource(R.string.placeholder_read_our_privacy_policy),
                trailingIcon = R.drawable.ic_share_box_line_24,
                onClick = {
                    Util.openBrowser(context = context, url = Constant.LINK_PRIVACY_POLICY)
                }
            )
        }
    }
}