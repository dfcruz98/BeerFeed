package com.example.beerfeed.data.remote

import com.example.beerfeed.data.objects.Beer

data class BeersResponse(
    val statusCode: Int?,
    val error: String?,
    val message: String?,
    val beers: List<Beer>?
)