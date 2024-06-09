package com.mrndevs.weatherapp.data.source.local.model

data class SettingsEntity(
    val isFirstRunApp: Boolean = true,
    val isDarkTheme: Boolean = false,
    val tempUnit: TempUnitEnum = TempUnitEnum.CELSIUS,
    val windSpeedUnit: WindSpeedUnitEnum = WindSpeedUnitEnum.KPH,
    val pressureUnit: PressureUnitEnum = PressureUnitEnum.HPA
)