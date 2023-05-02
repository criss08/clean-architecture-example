package com.example.cleanarquitectureexample.movie_list.presentation

sealed class MovieListViewState {
    object Loading : MovieListViewState()
    object Error : MovieListViewState()
    data class Content(val movieList: List<MovieCardViewState>) : MovieListViewState()
}