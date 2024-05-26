package com.mrndevs.weatherapp.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object Util {
    private const val ASSET_FILE_PREFIX = "file:///android_asset/"

    fun Int.getAssetPrefix(isDay: Boolean): String {
        return "${ASSET_FILE_PREFIX}${if (isDay) "day" else "night"}/$this.png"
    }

    fun Double.addDegreeSymbol(): String {
        return "${this.toInt()}\u00B0"
    }

    fun Int.getFormattedTime(): String {
        val instant = Instant.ofEpochSecond(this.toLong())
        val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }

    fun Int.getFormattedDate(): String {
        val instant = Instant.ofEpochSecond(this.toLong())
        val formatter = DateTimeFormatter.ofPattern("MMM, dd", Locale.getDefault())
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }

    fun Int.getFormattedDay(): String {
        val instant = Instant.ofEpochSecond(this.toLong())
        val formatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
            .withZone(ZoneId.systemDefault())
        val formattedInstant = formatter.format(instant)
        val formattedCurrent = formatter.format(Instant.now())
        val formattedTomorrow = formatter.format(Instant.now().plusSeconds(86400))

        return when (formattedInstant) {
            formattedCurrent -> "Today"
            formattedTomorrow -> "Tomorrow"
            else -> formattedInstant
        }
    }

    fun Int.checkUpdatedTime(): Boolean {
        val currentTime = getCurrentTime()
        return this + 900 < currentTime
    }

    fun getCurrentTime(): Long {
        return Instant.now().epochSecond
    }
}