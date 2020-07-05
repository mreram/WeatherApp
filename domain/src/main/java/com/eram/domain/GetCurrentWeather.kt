package com.eram.domain

import com.eram.domain.base.UseCase
import com.eram.domain.entity.Weather
import com.eram.domain.exceptions.IErrorHandler
import com.eram.domain.exceptions.InvalidCityId
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(private val repository: Repository, errorHandler: IErrorHandler) :
    UseCase<Weather, Int>(errorHandler) {

    override suspend fun run(params: Int?): Weather {
        if (params == null)
            throw InvalidCityId()
        else
            return repository.getCurrentWeather(params)
    }


}