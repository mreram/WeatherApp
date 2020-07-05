package com.eram.domain

import com.eram.domain.base.UseCase
import com.eram.domain.entity.TimeState
import com.eram.domain.exceptions.IErrorHandler
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetCurrentTimeState @Inject constructor(errorHandler: IErrorHandler) :
    UseCase<TimeState, Void>(errorHandler) {

    override suspend fun run(params: Void?): TimeState {
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("HH", Locale.US)
        return when (simpleDateFormat.format(date).toInt()) {
            in 0..6 -> {
                TimeState.Dawn
            }
            in 7..11 -> {
                TimeState.Morning
            }
            in 12..16 -> {
                TimeState.Noon
            }
            in 17..19 -> {
                TimeState.Evening
            }
            in 20..23 -> {
                TimeState.Night
            }
            else ->
                TimeState.Undefined

        }
    }


}