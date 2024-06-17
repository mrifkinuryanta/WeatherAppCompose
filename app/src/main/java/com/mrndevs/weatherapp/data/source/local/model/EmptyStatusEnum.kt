package com.mrndevs.weatherapp.data.source.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mrndevs.weatherapp.R

enum class EmptyStatusEnum(
    @StringRes val title: Int,
    @StringRes val placeholder: Int,
    @DrawableRes val iconLight: Int,
    @DrawableRes val iconDark: Int
) {
    SEARCH(
        title = R.string.title_search,
        placeholder = R.string.placeholder_message_search,
        iconLight = R.drawable.img_location_search_light,
        iconDark = R.drawable.img_location_search_dark
    ),
    EMPTY_SEARCH(
        title = R.string.title_empty_search,
        placeholder = R.string.placeholder_message_empty_search,
        iconLight = R.drawable.img_empty_light,
        iconDark = R.drawable.img_empty_dark
    ),
    FIRST_RUN_APP(
        title = R.string.title_welcome,
        placeholder = R.string.placeholder_message_welcome,
        iconLight = R.drawable.img_welcoming_light,
        iconDark = R.drawable.img_welcoming_dark
    ),
    OFFLINE(
        title = R.string.title_offline,
        placeholder = R.string.placeholder_message_offline,
        iconLight = R.drawable.img_offline_light,
        iconDark = R.drawable.img_offline_dark
    )
}