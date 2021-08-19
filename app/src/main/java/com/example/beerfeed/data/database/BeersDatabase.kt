package com.example.beerfeed.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beerfeed.data.database.dao.BeersDao
import com.example.beerfeed.data.database.dao.RemotePagesDao
import com.example.beerfeed.data.database.entities.Page
import com.example.beerfeed.data.objects.Beer

@Database(
    entities = [Beer::class, Page::class],
    version = 1,
    exportSchema = false
)
abstract class BeersDatabase : RoomDatabase() {
    abstract fun beersDao(): BeersDao
    abstract fun pagesDao(): RemotePagesDao
}