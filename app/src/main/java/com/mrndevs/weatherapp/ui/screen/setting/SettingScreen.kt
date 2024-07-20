package com.mrndevs.weatherapp.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.ui.screen.setting.view.SettingAbout
import com.mrndevs.weatherapp.ui.screen.setting.view.SettingFeedback
import com.mrndevs.weatherapp.ui.screen.setting.view.SettingGeneral
import com.mrndevs.weatherapp.ui.theme.SP16
import com.mrndevs.weatherapp.ui.theme.W600
import com.mrndevs.weatherapp.util.Constant
import com.mrndevs.weatherapp.util.Util.getColorGradient

@Composable
fun SettingScreen(viewModel: SettingViewModel = hiltViewModel(), onNavigateUp: () -> Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(uiState.settings) {
        viewModel.init()
    }

    SettingScreen(
        uiState = uiState,
        onNavigateUp = onNavigateUp,
        onSaveSettings = viewModel::saveSettings
    )
}

@Composable
fun SettingScreen(
    uiState: SettingUiState,
    onNavigateUp: () -> Unit,
    onSaveSettings: (SettingsEntity) -> Unit
) {
    Column(Modifier.background(getColorGradient())) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(R.string.placeholder_back_button),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.title_setting),
                style = SP16.W600,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Constant.DEFAULT_SPACING.dp)
        ) {
            SettingGeneral(uiState = uiState, onSaveSettings = onSaveSettings)
            SettingAbout()
            SettingFeedback()
        }
    }
}