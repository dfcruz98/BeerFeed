package com.example.beerfeed.data.objects

import com.google.gson.annotations.SerializedName

data class Beer(
    val id: Int,
    val name: String,
    @SerializedName("tagline")
    val tagLine: String,
    @SerializedName("first_brewed")
    val firstBrewed: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val abv: Double,
    val ibu: Double,
    @SerializedName("target_fg")
    val targetFg: Double,
    @SerializedName("target_og")
    val targetOg: Double,
    val ebc: Double,
    val srm: Double,
    val ph: Double,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double,
    val volume: Value,
    val boilVolume: Value,
    val method: Method,
    val ingredients: Ingredients,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("brewer_tips")
    val brewerTips: List<String>,
    @SerializedName("contributed_by")
    val contributedBy: String
)