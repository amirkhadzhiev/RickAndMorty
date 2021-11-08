package com.amir.rickandmorty.models


data class Character(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: Origin? = null,
    val location: Location? = null,
    val image: String? = null,
    val url: String? = null,
    val created: String? = null,
    val air_date: String? = null,
    val episodes: List<String>
)

data class Origin(
        val name: String,
        val url: String
)

data class Location(
        val name: String,
        val url: String
)

