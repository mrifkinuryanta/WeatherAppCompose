package com.mrndevs.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.mrndevs.weatherapp.R

val poppinsFamily = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_medium, FontWeight.Medium)
)

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = poppinsFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = poppinsFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = poppinsFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = poppinsFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = poppinsFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = poppinsFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = poppinsFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = poppinsFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = poppinsFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = poppinsFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = poppinsFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = poppinsFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = poppinsFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = poppinsFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = poppinsFamily)
)