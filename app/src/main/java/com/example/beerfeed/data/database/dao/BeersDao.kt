package com.example.beerfeed.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("UPDATE beer SET favorite = :favorite WHERE id = :id")
    fun updateFavorite(id: Long, favorite: Boolean)

    @Query("DELETE FROM beer")
    suspend fun clearAll()

}