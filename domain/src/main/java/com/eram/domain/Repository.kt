package com.eram.domain

import com.eram.domain.entity.Weather

interface Repository {

    suspend fun getCurrentWeather(cityId: Int): Weather

    suspend fun getMultipleDaysWeather(cityId: Int, cnt: Int): List<Weather>

    suspend fun getMultipleTimesWeather(cityId: Int, cnt: Int): List<Weather>

}