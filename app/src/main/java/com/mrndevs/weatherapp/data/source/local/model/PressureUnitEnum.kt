package com.mrndevs.weatherapp.data.source.local.model

import androidx.annotation.DrawableRes
import com.mrndevs.weatherapp.R

enum class PressureUnitEnum(val value: String, @DrawableRes val icon: Int) {
    HPA(value = "hPa", icon = R.drawable.ic_speed_up_line_24),
    MBAR(value = "mbar", icon = R.drawable.ic_speed_up_line_24),
    INHG(value = "inHg", icon = R.drawable.ic_speed_up_line_24);

    companion object {
        val pressureUnitOption
            get() = entries.map { item ->
                OptionData(label = item.value, value = item)
            }
    }
}