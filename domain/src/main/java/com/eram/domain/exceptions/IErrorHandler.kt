package com.eram.domain.exceptions

interface IErrorHandler {
    fun handleException(throwable: Throwable?): ErrorModel
}