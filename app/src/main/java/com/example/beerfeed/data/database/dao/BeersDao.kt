package com.example.beerfeed.data.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.beerfeed.data.objects.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Beer>)

    @Query("SELECT * FROM beer")
    fun pagingSource(): PagingSource<Int, Beer>

    @Query("SELECT * FROM beer")
    fun getAll(): Flow<List<Beer>>

    @Query("SELECT * FROM beer WHERE id = :id")
    fun getBeer(id: Long): Flow<Beer>

    @Query("SELECT * FROM beer WHERE favorite = 1")
    fun getFavorites(): Flow<List<Beer>>

    @Update
    fun update(beer: Beer)

    @Query("DELETE FROM beer")
    suspend fun clearAll()

}