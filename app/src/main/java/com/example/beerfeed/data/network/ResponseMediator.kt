package com.example.beerfeed.data.network

import retrofit2.Response


data class ResponseMediator<T>(
    val result: StatusResult<T>,
) {

    companion object {

        fun <T> checkResponse(data: Response<T>): StatusResult<T> {
            if (!data.isSuccessful) {
                return StatusResult.Error(data.message())
            }

            return StatusResult.Success(data.body())
        }

        fun <T> failure(exception: Exception): StatusResult<T> {
            return StatusResult.ExceptionResult(exception)
        }
    }
}

sealed class StatusResult<T> {
    data class Success<T>(val value: T?) : StatusResult<T>()
    data class Error<T>(val message: String?) : StatusResult<T>()
    data class ExceptionResult<T>(val ex: Exception) : StatusResult<T>()
}