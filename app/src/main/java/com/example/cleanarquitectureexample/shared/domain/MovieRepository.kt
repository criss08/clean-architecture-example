package com.example.cleanarquitectureexample.shared.domain

import com.example.cleanarquitectureexample.movie_details.domain.MovieDetail
import com.example.cleanarquitectureexample.movie_list.domain.Movie

interface MovieRepository {
    suspend fun getMovieList(): Result<List<Movie>>
    suspend fun getMovieDetails(movieId: String): Result<MovieDetail>
}