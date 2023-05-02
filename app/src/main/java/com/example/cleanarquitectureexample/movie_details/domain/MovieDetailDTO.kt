package com.example.cleanarquitectureexample.movie_details.domain

import com.example.cleanarquitectureexample.shared.domain.Constants
import com.example.cleanarquitectureexample.movie_details.data.MovieDetailEntity as Entity
import com.example.cleanarquitectureexample.movie_details.domain.MovieDetail as Domain

fun Entity.toDomain(): Domain {
    return Domain(
        title = title,
        backdropPath = backdropPath,
        genreEntities = genreEntities,
        releaseDate = releaseDate,
        id = id,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = Constants.TMDB_PHOTO_PATH + "$posterPath"
    )
}