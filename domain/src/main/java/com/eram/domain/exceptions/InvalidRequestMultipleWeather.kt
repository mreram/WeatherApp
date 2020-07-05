package com.eram.domain.exceptions

import java.lang.IllegalArgumentException

/**
 * Invalid place id
 */
class InvalidRequestMultipleWeather : IllegalArgumentException()
{
    override val message: String?
        get() = "Please check your RequestMultipleDays"
}
