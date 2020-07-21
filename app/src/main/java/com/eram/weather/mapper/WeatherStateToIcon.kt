package com.eram.weather.mapper

import com.eram.domain.entity.TimeState
import com.eram.domain.entity.WeatherState

fun getWeatherIcon(state: WeatherState, currentTimeState: TimeState): Int {
    return WeatherStateMap.icon[Pair(
        state,
        currentTimeState
    )] ?: WeatherStateMap.icon[Pair(
        state,
        TimeState.Undefined
    )]!!
}