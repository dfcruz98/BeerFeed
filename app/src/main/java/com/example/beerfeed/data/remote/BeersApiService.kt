package com.example.beerfeed.data.remote

import com.example.beerfeed.data.objects.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApiService {
    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<Beer>
}