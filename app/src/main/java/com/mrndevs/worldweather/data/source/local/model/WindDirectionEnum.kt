package com.mrndevs.worldweather.data.source.local.model

enum class WindDirectionEnum(val label: String, val mark: String) {
    NORTH(label = "North", mark = "N"),
    NORTH_NORTH_EAST(label = "North North East", mark = "NNE"),
    NORTH_EAST(label = "North East", mark = "NE"),
    EAST_NORTH_EAST(label = "East North East", mark = "ENE"),
    EAST(label = "East", mark = "E"),
    EAST_SOUTH_EAST(label = "East South East", mark = "ESE"),
    SOUTH_EAST(label = "South East", mark = "SE"),
    SOUTH_SOUTH_EAST(label = "South South East", mark = "SSE"),
    SOUTH(label = "South", mark = "S"),
    SOUTH_SOUTH_WEST(label = "South South West", mark = "SSW"),
    SOUTH_WEST(label = "South West", mark = "SW"),
    WEST_SOUTH_WEST(label = "West South West", mark = "WSW"),
    WEST(label = "West", mark = "W"),
    WEST_NORTH_WEST(label = "West North West", mark = "WNW"),
    NORTH_WEST(label = "North West", mark = "NW"),
    NORTH_NORTH_WEST(label = "North North West", mark = "NNW");

    companion object {
        fun getWindDirection(mark: String): WindDirectionEnum {
            return WindDirectionEnum.entries.find { it.mark == mark } ?: NORTH
        }
    }
}