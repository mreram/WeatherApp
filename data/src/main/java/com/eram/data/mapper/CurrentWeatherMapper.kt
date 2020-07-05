package com.eram.data.mapper

import com.eram.data.remote.model.CurrentWeatherResponse
import com.eram.domain.entity.Weather
import kotlin.math.roundToInt

fun mapToWeather(currentWeather: CurrentWeatherResponse): Weather {
    val icon = currentWeather.weather.first().icon

    return Weather(
        currentWeather.main.temp_min.roundToInt(),
        currentWeather.main.temp_max.roundToInt(),
        currentWeather.main.temp.roundToInt(),
        mapWeatherIcon(icon)
    )
}
