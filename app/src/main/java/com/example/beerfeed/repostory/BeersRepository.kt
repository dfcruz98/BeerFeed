package com.example.beerfeed.repostory

import androidx.paging.PagingData
import com.example.beerfeed.data.objects.Beer
import kotlinx.coroutines.flow.Flow

interface BeersRepository {
    fun getBeers(): Flow<PagingData<Beer>>
    fun getBeer(id: Long): Flow<Beer>
    fun getFavorites(): Flow<List<Beer>>
    fun updateFavorite(id: Long, favorite: Boolean)
}