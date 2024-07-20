package com.mrndevs.worldweather.data.source.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mrndevs.worldweather.R

enum class EmptyStatusEnum(
    @StringRes val title: Int,
    @StringRes val placeholder: Int,
    @DrawableRes val image: Int
) {
    SEARCH(
        title = R.string.title_search,
        placeholder = R.string.placeholder_message_search,
        image = R.drawable.img_search
    ),
    EMPTY_SEARCH(
        title = R.string.title_empty_search,
        placeholder = R.string.placeholder_message_empty_search,
        image = R.drawable.img_empty
    ),
    FIRST_RUN_APP(
        title = R.string.title_welcome,
        placeholder = R.string.placeholder_message_welcome,
        image = R.drawable.img_welcoming
    ),
    OFFLINE(
        title = R.string.title_offline,
        placeholder = R.string.placeholder_message_offline,
        image = R.drawable.img_offline
    )
}