package com.mrndevs.worldweather.data.source.local.model

import androidx.annotation.DrawableRes

data class OptionData<T>(
    @DrawableRes val icon: Int? = null,
    val label: String,
    val value: T
)
