package com.example.cleanarquitectureexample.movie_list.domain

import java.util.*

data class Movie(
    val title: String?,
    val backdropPath: String?,
    val mediaType: String?,
    val releaseDate: Date?,
    val firstAirDate: Date?,
    val genreIds: List<Long>,
    val name: String?,
    val originCountry: List<String>?,
    val id: Long,
    val originalLanguage: String,
    val originalName: String?,
    val overview: String?,
    val popularity: Float,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Long
)