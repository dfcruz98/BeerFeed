package com.example.beerfeed.data.network

import com.example.beerfeed.data.objects.Beer
import retrofit2.Response

class BeersApiClient(private val api: BeersApiService) {

    suspend fun getBeers(page: Int, perPage: Int): StatusResult<List<Beer>> {
        return safeApiCall { api.getBeers(page, perPage) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): StatusResult<T> {
        return try {
            ResponseMediator.checkResponse(apiCall.invoke())
        } catch (e: Exception) {
            ResponseMediator.failure(e)
        }
    }
}