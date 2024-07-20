package com.mrndevs.worldweather.ui.screen.setting.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.R
import com.mrndevs.worldweather.data.source.local.model.PressureUnitEnum
import com.mrndevs.worldweather.data.source.local.model.SettingsEntity
import com.mrndevs.worldweather.data.source.local.model.TempUnitEnum
import com.mrndevs.worldweather.data.source.local.model.WindSpeedUnitEnum
import com.mrndevs.worldweather.ui.component.CardItem
import com.mrndevs.worldweather.ui.component.SingleSelectBottomSheet
import com.mrndevs.worldweather.ui.screen.setting.SettingUiState
import com.mrndevs.worldweather.ui.screen.setting.component.SettingItem
import com.mrndevs.worldweather.ui.screen.setting.component.SettingTitle

@Composable
fun SettingGeneral(uiState: SettingUiState, onSaveSettings: (SettingsEntity) -> Unit) {
    var showTempUnitSheet by remember { mutableStateOf(false) }
    var showWindSpeedUnitSheet by remember { mutableStateOf(false) }
    var showPressureUniSheet by remember { mutableStateOf(false) }

    val tempUnitList = TempUnitEnum.tempUnitOption
    val windSpeedUnitList = WindSpeedUnitEnum.windSpeedUnitOption
    val pressureUnitList = PressureUnitEnum.pressureUnitOption

    CardItem(modifier = Modifier.padding(horizontal = 18.dp)) {
        Column {
            val tempUnit = uiState.settings.tempUnit
            val windSpeedUnit = uiState.settings.windSpeedUnit
            val pressureUnit = uiState.settings.pressureUnit

            SettingTitle(title = stringResource(R.string.title_general))
            SettingItem(
                icon = tempUnit.icon,
                title = stringResource(R.string.title_temperature_unit),
                description = tempUnit.value,
                trailingIcon = R.drawable.ic_arrow_right_s_line_24,
                onClick = { showTempUnitSheet = true }
            )
            SettingItem(
                icon = windSpeedUnit.icon,
                title = stringResource(R.string.title_wind_speed_unit),
                description = windSpeedUnit.value,
                trailingIcon = R.drawable.ic_arrow_right_s_line_24,
                onClick = { showWindSpeedUnitSheet = true }
            )
            SettingItem(
                icon = pressureUnit.icon,
                title = stringResource(R.string.title_pressure_unit),
                description = pressureUnit.value,
                trailingIcon = R.drawable.ic_arrow_right_s_line_24,
                onClick = { showPressureUniSheet = true }
            )
        }
    }

    SingleSelectBottomSheet(
        onDismissRequest = { state -> showTempUnitSheet = state },
        items = tempUnitList,
        value = uiState.settings.tempUnit,
        showSheet = showTempUnitSheet,
        onApply = { result ->
            onSaveSettings(uiState.settings.copy(tempUnit = result))
        }
    )

    SingleSelectBottomSheet(
        onDismissRequest = { state -> showWindSpeedUnitSheet = state },
        items = windSpeedUnitList,
        value = uiState.settings.windSpeedUnit,
        showSheet = showWindSpeedUnitSheet,
        onApply = { result ->
            onSaveSettings(uiState.settings.copy(windSpeedUnit = result))
        }
    )

    SingleSelectBottomSheet(
        onDismissRequest = { state -> showPressureUniSheet = state },
        items = pressureUnitList,
        value = uiState.settings.pressureUnit,
        showSheet = showPressureUniSheet,
        onApply = { result ->
            onSaveSettings(uiState.settings.copy(pressureUnit = result))
        }
    )
}