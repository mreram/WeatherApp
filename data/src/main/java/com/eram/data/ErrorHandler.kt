package com.eram.data

import com.eram.domain.exceptions.ErrorModel
import com.eram.domain.exceptions.IErrorHandler
import kotlinx.coroutines.CancellationException
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorHandler : IErrorHandler {

    override fun handleException(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                getHttpError(throwable.response()?.errorBody())
            }

            is CancellationException -> {
                ErrorModel(
                    "Canceled by user!",
                    0,
                    ErrorModel.ErrorStatus.CANCELED
                )
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel(
                    throwable.message,
                    ErrorModel.ErrorStatus.TIMEOUT
                )
            }

            // handle connection error
            is IOException -> {
                ErrorModel(
                    throwable.message,
                    ErrorModel.ErrorStatus.NO_CONNECTION
                )
            }
            else -> null
        }
        return errorModel ?: ErrorModel(
            "No Defined Error!",
            0,
            ErrorModel.ErrorStatus.BAD_RESPONSE
        )
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            // use response body to get error detail
            ErrorModel(
                body.toString(),
                400,
                ErrorModel.ErrorStatus.BAD_RESPONSE
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(
                message = e.message,
                errorStatus = ErrorModel.ErrorStatus.NOT_DEFINED
            )
        }

    }
}