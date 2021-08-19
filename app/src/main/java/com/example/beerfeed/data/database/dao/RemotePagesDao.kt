package com.example.beerfeed.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beerfeed.data.database.entities.Page
import kotlinx.coroutines.flow.Flow

@Dao
interface RemotePagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<Page>)

    @Query("SELECT * FROM page")
    fun getAll(): Flow<List<Page>>

    @Query("SELECT * FROM page WHERE beerId = :id")
    suspend fun getBeerPage(id: Long): Page?

    @Query("DELETE FROM page")
    suspend fun clearAll()
}
