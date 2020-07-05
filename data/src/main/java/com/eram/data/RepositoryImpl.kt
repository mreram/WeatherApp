package com.eram.data

import com.eram.data.local.LocalDataSource
import com.eram.data.remote.RemoteDataSource
import com.eram.domain.Repository
import com.eram.domain.entity.Weather
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getCurrentWeather(cityId: Int): Weather {
        return remoteDataSource.getCurrentWeather(cityId)
    }

    override suspend fun getMultipleDaysWeather(cityId: Int, cnt: Int): List<Weather> {
        return remoteDataSource.getMultipleDaysWeather(cityId, cnt)
    }

    override suspend fun getMultipleTimesWeather(cityId: Int, cnt: Int): List<Weather> {
        return remoteDataSource.getMultipleTimesWeather(cityId, cnt)
    }

}