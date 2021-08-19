package com.example.beerfeed.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity that stores the next and previous page used to fetch Beers from the api
 */
@Entity(tableName = "page")
data class Page(
    @PrimaryKey(autoGenerate = false) val beerId: Long,
    val prevPage: Int?,
    val nextPage: Int?
)
