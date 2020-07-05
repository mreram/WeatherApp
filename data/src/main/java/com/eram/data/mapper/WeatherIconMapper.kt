package com.eram.data.mapper

import com.eram.domain.entity.WeatherState

fun mapWeatherIcon(icon: String): WeatherState {
    icon.run {
        return when {
            contains("01") ->
                WeatherState.ClearSky
            contains("02") ->
                WeatherState.FewClouds
            contains("03") ->
                WeatherState.ScatteredClouds
            contains("04") ->
                WeatherState.BrokenClouds
            contains("09") ->
                WeatherState.ShowerRain
            contains("10") ->
                WeatherState.Rain
            contains("11") ->
                WeatherState.Thunderstorm
            contains("13") ->
                WeatherState.Snow
            contains("50") ->
                WeatherState.Mist
            else -> WeatherState.ClearSky
        }
    }
}