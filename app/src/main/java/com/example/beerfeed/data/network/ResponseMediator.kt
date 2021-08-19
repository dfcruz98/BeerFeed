package com.example.beerfeed.data.network

import retrofit2.Response


data class ResponseMediator<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {

    companion object {

        fun <T> result(data: Response<T>): ResponseMediator<T> {
            if (!data.isSuccessful) {
                return ResponseMediator(Status.Failure, null, null)
            }

            return success(data)
        }

        fun <T> success(data: Response<T>): ResponseMediator<T> {
            return ResponseMediator(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): ResponseMediator<T> {
            return ResponseMediator(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }
}