package com.eram.domain

import com.eram.domain.base.UseCase
import com.eram.domain.entity.RequestMultipleWeather
import com.eram.domain.entity.Weather
import com.eram.domain.exceptions.IErrorHandler
import com.eram.domain.exceptions.InvalidCityId
import com.eram.domain.exceptions.InvalidRequestMultipleWeather
import javax.inject.Inject

class GetMultipleTimesWeather @Inject constructor(
    private val repository: Repository,
    errorHandler: IErrorHandler
) : UseCase<List<Weather>, RequestMultipleWeather>(errorHandler) {

    override suspend fun run(params: RequestMultipleWeather?): List<Weather> {
        when {
            params == null -> throw InvalidRequestMultipleWeather()
            params.cityId == null -> throw InvalidCityId()
            else -> return repository.getMultipleTimesWeather(params.cityId, params.cnt)
        }
    }

}