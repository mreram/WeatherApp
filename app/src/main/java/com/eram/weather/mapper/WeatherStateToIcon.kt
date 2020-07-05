package com.eram.weather.mapper

import com.eram.domain.entity.TimeState
import com.eram.domain.entity.WeatherState
import com.eram.weather.R

fun getWeatherIcon(state: WeatherState, currentTimeState: TimeState): Int {
    return when (state) {
        WeatherState.ClearSky -> {
            when (currentTimeState) {
                TimeState.Morning ->
                    R.drawable.ic_clear_sky_morning
                TimeState.Night ->
                    R.drawable.ic_clear_sky_night
                TimeState.Dawn ->
                    R.drawable.ic_clear_sky_dawn
                TimeState.Evening ->
                    R.drawable.ic_clear_sky_evening
                else -> R.drawable.ic_clear_sky_morning
            }
        }
        WeatherState.FewClouds -> {
            when (currentTimeState) {
                TimeState.Morning ->
                    R.drawable.ic_few_cloud_morning
                TimeState.Night ->
                    R.drawable.ic_few_clound_night
                TimeState.Dawn ->
                    R.drawable.ic_few_cloud_dawn
                TimeState.Evening ->
                    R.drawable.ic_few_cloud_evening
                else -> R.drawable.ic_few_cloud_morning
            }
        }
        WeatherState.ScatteredClouds ->
            R.drawable.ic_scattered_clouds
        WeatherState.Rain ->
            R.drawable.ic_rain
        WeatherState.Mist ->
            R.drawable.ic_mist
        WeatherState.ShowerRain ->
            R.drawable.ic_shower_raint
        WeatherState.Thunderstorm ->
            R.drawable.ic_thunderstorm
        WeatherState.Snow ->
            R.drawable.ic_snow
        WeatherState.BrokenClouds ->
            R.drawable.ic_broken_cloud

    }
}