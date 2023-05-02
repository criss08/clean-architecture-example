package com.example.cleanarquitectureexample.movie_details.data

import com.google.gson.annotations.SerializedName

data class GenreEntity(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null
)