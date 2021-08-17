package com.example.beerfeed.data.objects

import com.google.gson.annotations.SerializedName

data class Method(
    @SerializedName("mash_temp")
    val mashTemp: List<Mash>,
    @SerializedName("fermentation")
    val fermentation: Fermentation,
    val twist: String?
)