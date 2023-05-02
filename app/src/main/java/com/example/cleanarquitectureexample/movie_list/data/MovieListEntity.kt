package com.example.cleanarquitectureexample.movie_list.data

import com.google.gson.annotations.SerializedName

data class MovieListEntity(
    val page: Long,
    val results: List<Movie>,
    @SerializedName(value = "total_pages")
    val totalPage: Long,
    @SerializedName(value = "total_results")
    val totalResults: Long
)
