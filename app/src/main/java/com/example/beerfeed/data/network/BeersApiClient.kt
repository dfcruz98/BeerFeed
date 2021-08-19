package com.example.beerfeed.data.network

import com.example.beerfeed.data.objects.Beer
import retrofit2.Response

class BeersApiClient(private val api: BeersApiService) {

    suspend fun getBeers(page: Int, perPage: Int): ResponseMediator<List<Beer>> {
        return safeApiCall { api.getBeers(page, perPage) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): ResponseMediator<T> {
        return try {
            ResponseMediator.result(apiCall.invoke())
        } catch (e: Exception) {
            ResponseMediator.failure(e)
        }
    }
}