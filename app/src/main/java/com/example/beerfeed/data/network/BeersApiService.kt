package com.example.beerfeed.data.network

import com.example.beerfeed.data.objects.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApiService {
    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<Beer>>
}