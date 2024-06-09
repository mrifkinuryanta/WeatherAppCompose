package com.mrndevs.weatherapp.data.source.local.model

import androidx.annotation.DrawableRes
import com.mrndevs.weatherapp.R

enum class WindSpeedUnitEnum(val value: String, @DrawableRes val icon: Int) {
    KPH(value = "km/h", icon = R.drawable.ic_windy_line_24),
    MPH(value = "mph", icon = R.drawable.ic_windy_line_24)
}