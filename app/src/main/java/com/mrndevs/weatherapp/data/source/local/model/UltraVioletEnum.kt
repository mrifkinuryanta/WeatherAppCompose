package com.mrndevs.weatherapp.data.source.local.model

enum class UltraVioletEnum(val value: List<Int>, val label: String, val color: Long) {
    LOW(
        value = listOf(0, 2),
        label = "Low",
        color = 0xFF00FF00
    ),
    MODERATE(
        value = listOf(3, 5),
        label = "Moderate",
        color = 0xFFFFFF00
    ),
    HIGH(
        value = listOf(6, 7),
        label = "High",
        color = 0xFFFFA500
    ),
    VERY_HIGH(
        value = listOf(8, 10),
        label = "Very High",
        color = 0xFFFF0000
    ),
    EXTREME(
        value = listOf(11, 100),
        label = "Extreme",
        color = 0xFFEE82EE
    );

    companion object {
        fun getUVIndex(uvIndex: Int): UltraVioletEnum {
            return when (uvIndex) {
                in LOW.value[0]..LOW.value[1] -> LOW
                in MODERATE.value[0]..MODERATE.value[1] -> MODERATE
                in HIGH.value[0]..HIGH.value[1] -> HIGH
                in VERY_HIGH.value[0]..VERY_HIGH.value[1] -> VERY_HIGH
                in EXTREME.value[0]..EXTREME.value[1] -> EXTREME
                else -> LOW
            }
        }
    }
}