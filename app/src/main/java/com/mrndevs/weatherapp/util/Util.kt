package com.mrndevs.weatherapp.util

import android.content.Context
import android.content.Intent
import android.net.Uri
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

    fun Int.getFormattedTime(timeZone: String): String {
        val instant = Instant.ofEpochSecond(this.toLong())
        val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
            .withZone(ZoneId.of(timeZone))
        return formatter.format(instant)
    }

    fun Int.getFormattedDate(timeZone: String): String {
        val instant = Instant.ofEpochSecond(this.toLong())
        val formatter = DateTimeFormatter.ofPattern("MMM, dd", Locale.getDefault())
            .withZone(ZoneId.of(timeZone))
        return formatter.format(instant)
    }

    fun Int.getFormattedDay(timeZone: String): String {
        val instant = Instant.ofEpochSecond(this.toLong())
        val formatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
            .withZone(ZoneId.of(timeZone))
        val formattedInstant = formatter.format(instant)
        val formattedCurrent = formatter.format(Instant.now())
        val formattedTomorrow = formatter.format(Instant.now().plusSeconds(86400))

        return when (formattedInstant) {
            formattedCurrent -> "Today"
            formattedTomorrow -> "Tomorrow"
            else -> formattedInstant
        }
    }

    fun openBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}