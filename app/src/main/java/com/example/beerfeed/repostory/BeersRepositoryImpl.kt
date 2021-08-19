package com.example.beerfeed.repostory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.beerfeed.data.database.BeersDatabase
import com.example.beerfeed.data.database.dao.BeersDao
import com.example.beerfeed.data.network.BeersApiClient
import com.example.beerfeed.data.objects.Beer
import com.example.beerfeed.data.paging_mediator.PagingRemoteMediator
import kotlinx.coroutines.flow.Flow

class BeersRepositoryImpl(
    private val api: BeersApiClient,
    private val beersBd: BeersDao,
    private val database: BeersDatabase,
) : BeersRepository {

    @ExperimentalPagingApi
    override fun getBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = PagingRemoteMediator(api, database),
            pagingSourceFactory = { beersBd.pagingSource() }
        ).flow
    }

    override fun getBeer(id: Long): Flow<Beer> {
        return beersBd.getBeer(id)
    }

    override fun getFavorites(): Flow<List<Beer>> {
        return beersBd.getFavorites()
    }

    override fun updateFavorite(id: Long, favorite: Boolean) {
        beersBd.updateFavorite(id, favorite)
    }
}