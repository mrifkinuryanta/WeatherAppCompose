package com.mrndevs.weatherapp.ui.screen.test

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

object Utils {
    private fun Context.findActivity(): Activity? {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    @Composable
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    fun Context.getWidthSizeClass(): WindowWidthSizeClass {
        val activity = this.findActivity()
        return if (activity != null) {
            calculateWindowSizeClass(activity).widthSizeClass
        } else {
            WindowWidthSizeClass.Compact
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    fun Context.getHeightSizeClass(): WindowHeightSizeClass {
        val activity = this.findActivity()
        return if (activity != null) {
            calculateWindowSizeClass(activity).heightSizeClass
        } else {
            WindowHeightSizeClass.Compact
        }
    }

    @Composable
    fun Context.getIsCompact(): Boolean {
        return this.getWidthSizeClass() == WindowWidthSizeClass.Compact
    }
}