package com.example.beerfeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beerfeed.data.local.dao.BeersDao
import com.example.beerfeed.data.objects.Beer

@Database(
    entities = [Beer::class],
    version = 1,
    exportSchema = false
)
abstract class BeersDatabase : RoomDatabase() {
    abstract fun beersDao(): BeersDao
}