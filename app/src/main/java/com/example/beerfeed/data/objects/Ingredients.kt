package com.example.beerfeed.data.objects

data class Ingredients(
    val malt: List<Ingredient>,
    val hops: List<Hops>,
    val yeast: String
)

data class Ingredient(
    val name: String,
    val amount: Value,
)