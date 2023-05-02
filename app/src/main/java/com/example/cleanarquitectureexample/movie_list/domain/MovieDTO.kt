package com.example.cleanarquitectureexample.movie_list.domain

import com.example.cleanarquitectureexample.movie_list.data.Movie as Entity
import com.example.cleanarquitectureexample.movie_list.domain.Movie as Domain

fun Entity.toDomain(): Domain {
    return Domain(
        title = title,
        backdropPath = backdropPath,
        mediaType = mediaType,
        releaseDate = releaseDate,
        firstAirDate = firstAirDate,
        genreIds = genreIds ?: listOf(),
        name = name,
        originCountry = originCountry,
        id = id,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity ?: 0F,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath",
        voteAverage = voteAverage ?: 0F,
        voteCount = voteCount ?: 0L,
    )
}