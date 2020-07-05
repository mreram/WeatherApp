package com.eram.domain.base

import com.eram.domain.exceptions.ErrorModel

interface UseCaseCallback<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}