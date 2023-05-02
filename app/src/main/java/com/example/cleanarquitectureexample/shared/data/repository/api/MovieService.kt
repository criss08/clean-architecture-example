package com.example.cleanarquitectureexample.shared.data.repository.api

import com.example.cleanarquitectureexample.movie_details.data.MovieDetailEntity
import com.example.cleanarquitectureexample.movie_list.data.MovieListEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService{
    @GET(ApiClient.APIConstants.TMDB_TOP_RATED)
    suspend fun getMovieList(): MovieListEntity

    @GET(ApiClient.APIConstants.TMDB_MOVIE_DETAIL)
    suspend fun getMovieDetails(@Path(value="movieId") movieId: String): MovieDetailEntity
}