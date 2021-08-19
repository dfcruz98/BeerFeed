package com.example.beerfeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beerfeed.data.local.dao.BeersDao
import com.example.beerfeed.data.local.dao.RemotePagesDao
import com.example.beerfeed.data.local.entities.Page
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