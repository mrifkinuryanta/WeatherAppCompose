package com.mrndevs.weatherapp.ui.screen.setting

import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity

data class SettingUiState(
    val settings: SettingsEntity = SettingsEntity()
)
