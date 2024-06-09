package com.mrndevs.weatherapp.data.source.local.model

import androidx.annotation.DrawableRes
import com.mrndevs.weatherapp.R

enum class TempUnitEnum(val value: String, @DrawableRes val icon: Int) {
    CELSIUS(value = "Celsius", icon = R.drawable.ic_celsius_line_24),
    FAHRENHEIT(value = "Fahrenheit", icon = R.drawable.ic_fahrenheit_line_24)
}