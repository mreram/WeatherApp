package com.eram.weather.mapper

import com.eram.domain.entity.TimeState
import com.eram.domain.entity.WeatherState
import com.eram.weather.R

object WeatherStateMap {
    val icon = mutableMapOf<Pair<WeatherState, TimeState>, Int>()
    init {
        icon += Pair(WeatherState.ClearSky, TimeState.Morning) to R.drawable.ic_clear_sky_morning
        icon += Pair(WeatherState.ClearSky, TimeState.Night) to R.drawable.ic_clear_sky_night
        icon += Pair(WeatherState.ClearSky, TimeState.Dawn) to R.drawable.ic_clear_sky_dawn
        icon += Pair(WeatherState.ClearSky, TimeState.Evening) to R.drawable.ic_clear_sky_evening
        icon += Pair(WeatherState.ClearSky, TimeState.Undefined) to R.drawable.ic_clear_sky_morning

        icon += Pair(WeatherState.FewClouds, TimeState.Morning) to R.drawable.ic_few_cloud_morning
        icon += Pair(WeatherState.FewClouds, TimeState.Night) to R.drawable.ic_few_cloud_night
        icon += Pair(WeatherState.FewClouds, TimeState.Dawn) to R.drawable.ic_few_cloud_dawn
        icon += Pair(WeatherState.FewClouds, TimeState.Evening) to R.drawable.ic_few_cloud_evening
        icon += Pair(WeatherState.FewClouds, TimeState.Undefined) to R.drawable.ic_few_cloud_morning

        icon += Pair(WeatherState.ScatteredClouds, TimeState.Undefined) to R.drawable.ic_scattered_clouds
        icon += Pair(WeatherState.Rain, TimeState.Undefined) to R.drawable.ic_rain
        icon += Pair(WeatherState.Mist, TimeState.Undefined) to R.drawable.ic_mist
        icon += Pair(WeatherState.ShowerRain, TimeState.Undefined) to R.drawable.ic_shower_raint
        icon += Pair(WeatherState.Thunderstorm, TimeState.Undefined) to R.drawable.ic_thunderstorm
        icon += Pair(WeatherState.Snow, TimeState.Undefined) to R.drawable.ic_snow
        icon += Pair(WeatherState.BrokenClouds, TimeState.Undefined) to R.drawable.ic_broken_cloud
    }
}