package com.example.beerfeed.data.paging_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.beerfeed.data.database.BeersDatabase
import com.example.beerfeed.data.database.dao.BeersDao
import com.example.beerfeed.data.database.dao.RemotePagesDao
import com.example.beerfeed.data.database.entities.Page
import com.example.beerfeed.data.network.BeersApiClient
import com.example.beerfeed.data.network.StatusResult
import com.example.beerfeed.data.objects.Beer
import retrofit2.HttpException
import java.io.IOException

private const val FIRST_PAGE_INDEX = 1

@ExperimentalPagingApi
class PagingRemoteMediator(
    private val api: BeersApiClient,
    private val db: BeersDatabase,
) : RemoteMediator<Int, Beer>() {

    private var beersDb: BeersDao = db.beersDao()
    private var pagesDb: RemotePagesDao = db.pagesDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Beer>): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> {
                val remoteKeys = getFirstPageKey(state)
                val prevKey = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastPageKey(state)
                val nextPage = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                nextPage
            }
        } ?: FIRST_PAGE_INDEX

        return try {

            return when (val response = api.getBeers(loadKey, 20)) {
                is StatusResult.Error -> {
                    MediatorResult.Error(Exception())
                }
                is StatusResult.ExceptionResult -> {
                    MediatorResult.Error(response.ex)
                }
                is StatusResult.Success<List<Beer>> -> {
                    dataManagement(loadType, response.value!!, loadKey)
                }
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun dataManagement(
        loadType: LoadType,
        data: List<Beer>,
        page: Int,
    ): MediatorResult {

        val endOfPaginationReached = data.isNullOrEmpty()

        db.withTransaction {

            val prevKey = if (page == FIRST_PAGE_INDEX) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1

            val keys = data.map {
                Page(beerId = it.id, prevPage = prevKey, nextPage = nextKey)
            }

            if (loadType == LoadType.REFRESH) {
                pagesDb.clearAll()
                beersDb.clearAll()
            }

            pagesDb.insertAll(keys)
            beersDb.insertAll(data)
        }

        return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    }

    private suspend fun getLastPageKey(state: PagingState<Int, Beer>): Page? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                pagesDb.getBeerPage(repo.id)
            }
    }

    private suspend fun getFirstPageKey(state: PagingState<Int, Beer>): Page? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                pagesDb.getBeerPage(repo.id)
            }
    }
}