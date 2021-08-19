package com.example.beerfeed.data.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beer")
data class Beer(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val name: String,
    var favorite: Boolean?,
    @SerializedName("tagline")
    val tagLine: String,
    @SerializedName("first_brewed")
    val firstBrewed: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    val abv: Double,
    val ibu: Double,
    @SerializedName("target_fg")
    val targetFg: Double,
    @SerializedName("target_og")
    val targetOg: Double,
    val ebc: Double,
    val srm: Double,
    val ph: Double,
//    @SerializedName("attenuation_level")
//    val attenuationLevel: Double,
//    val volume: Value,
//    val boilVolume: Value,
//    val method: Method,
//    val ingredients: Ingredients,
//    @SerializedName("food_pairing")
//    val foodPairing: List<String>,
//    @SerializedName("brewer_tips")
//    val brewerTips: List<String>,
//    @SerializedName("contributed_by")
//    val contributedBy: String
)