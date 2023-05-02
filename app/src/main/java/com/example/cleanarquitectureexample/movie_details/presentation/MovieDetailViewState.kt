package com.example.cleanarquitectureexample.movie_details.presentation

import com.example.cleanarquitectureexample.movie_details.domain.MovieDetail

sealed class MovieDetailViewState {
    object Loading : MovieDetailViewState()
    data class Content(val movie: MovieDetail) : MovieDetailViewState()
    object Error : MovieDetailViewState()
}