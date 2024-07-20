package com.mrndevs.worldweather.ui.screen.setting

import com.mrndevs.worldweather.data.source.local.model.SettingsEntity

data class SettingUiState(
    val settings: SettingsEntity = SettingsEntity()
)
