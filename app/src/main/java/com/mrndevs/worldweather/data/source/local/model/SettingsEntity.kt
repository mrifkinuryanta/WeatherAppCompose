package com.mrndevs.worldweather.data.source.local.model

data class SettingsEntity(
    val isFirstRunApp: Boolean = true,
    val isDarkTheme: Boolean = false,
    val currentTimeZone: String = "",
    val currentLocation: String = "",
    val updateAt: Int = 0,
    val tempUnit: TempUnitEnum = TempUnitEnum.CELSIUS,
    val windSpeedUnit: WindSpeedUnitEnum = WindSpeedUnitEnum.KPH,
    val pressureUnit: PressureUnitEnum = PressureUnitEnum.HPA
)