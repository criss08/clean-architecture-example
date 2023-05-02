package com.example.cleanarquitectureexample.movie_details.data

import com.google.gson.annotations.SerializedName

data class ProductionCountryEntity(
    @SerializedName("iso_3166_1")
    var iso31661: String? = null,
    @SerializedName("name")
    var name: String? = null
)