package com.example.rickymortyapp.data

data class CharacterResponse(
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: Location,
    val origin: Origin,
    val dimension: String?
)

data class Location(
    val name: String
)

data class Origin(
    val name: String
)