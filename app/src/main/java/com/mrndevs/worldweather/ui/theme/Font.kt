package com.mrndevs.worldweather.ui.theme

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

inline val SP20
    get() = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

inline val SP18
    get() = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.15.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

inline val SP16
    get() = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

inline val SP14
    get() = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

inline val SP10
    get() = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.W500,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

inline val TextStyle.W600 get() = this.copy(fontWeight = FontWeight.W600)

inline val TextStyle.W400 get() = this.copy(fontWeight = FontWeight.W400)