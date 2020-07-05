package com.eram.data.remote

import android.content.Context
import com.eram.data.R
import com.eram.data.mapper.mapToDailyForecast
import com.eram.data.mapper.mapToForecast
import com.eram.data.mapper.mapToWeather
import com.eram.domain.entity.Weather
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

public class RemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val service: OpenWeatherApiService
) : RemoteDataSource {

    companion object {
    }

    override suspend fun getCurrentWeather(cityId: Int): Weather {
        val currentWeather = service.getCurrentWeather(
            cityId = cityId,
            units = "metric",
            appId = context.getString(R.string.app_id)
        )

        return mapToWeather(currentWeather)
    }

    override suspend fun getMultipleDaysWeather(cityId: Int, cnt: Int): List<Weather> {
        val multipleDaysWeather = service.getMultipleDaysWeather(
            cityId,
            "metric",
            "en",
            cnt,
            context.getString(R.string.app_id)
        )

        return mapToDailyForecast(multipleDaysWeather)
    }

    override suspend fun getMultipleTimesWeather(cityId: Int, cnt: Int): List<Weather> {
        val multipleTimesWeather = service.getMultipleTimesWeather(
            cityId,
            "metric",
            "en",
            cnt,
            context.getString(R.string.app_id)
        )

        return mapToForecast(multipleTimesWeather)
    }

}