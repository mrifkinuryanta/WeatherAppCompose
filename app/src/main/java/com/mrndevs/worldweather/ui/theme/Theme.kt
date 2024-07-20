package com.mrndevs.worldweather.ui.theme

import android.app.Activity
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    secondary = secondaryLight,
    tertiary = Color.White,
    primaryContainer = primaryContainerLight,
    background = backgroundLight,
    surface = backgroundLight,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    secondary = secondaryDark,
    tertiary = Color.White,
    primaryContainer = primaryContainerDark,
    background = backgroundDark,
    surface = backgroundDark,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

val LocalTheme = compositionLocalOf { ThemeState() }

@Composable
fun worldweatherTheme(
    content: @Composable () -> Unit
) {
    val isDarkTheme = LocalTheme.current.isDarkTheme
    val targetColorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme
    val colorScheme by animateColorSchemeAsState(targetColorScheme)

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Black.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun animateColorSchemeAsState(targetColorScheme: ColorScheme): State<ColorScheme> {
    val currentColorScheme = remember { mutableStateOf(targetColorScheme) }

    val transition =
        updateTransition(targetState = targetColorScheme, label = "ColorSchemeTransition")
    transition.animateColor(label = "primaryColor") { state ->
        state.primary
    }.let { primaryColor ->
        currentColorScheme.value = currentColorScheme.value.copy(primary = primaryColor.value)
    }

    transition.animateColor(label = "secondaryColor") { state ->
        state.secondary
    }.let { secondaryColor ->
        currentColorScheme.value = currentColorScheme.value.copy(secondary = secondaryColor.value)
    }

    transition.animateColor(label = "primaryContainerColor") { state ->
        state.primaryContainer
    }.let { primaryContainerColor ->
        currentColorScheme.value =
            currentColorScheme.value.copy(primaryContainer = primaryContainerColor.value)
    }

    transition.animateColor(label = "backgroundColor") { state ->
        state.background
    }.let { backgroundColor ->
        currentColorScheme.value = currentColorScheme.value.copy(background = backgroundColor.value)
    }

    transition.animateColor(label = "surfaceColor") { state ->
        state.surface
    }.let { surfaceColor ->
        currentColorScheme.value = currentColorScheme.value.copy(surface = surfaceColor.value)
    }

    return currentColorScheme
}

data class ThemeState(
    val isDarkTheme: Boolean = false
)