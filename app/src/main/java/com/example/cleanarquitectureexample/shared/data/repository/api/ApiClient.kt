package com.example.cleanarquitectureexample.shared.data.repository.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    object APIConstants {
        private const val TMDB_API_KEY = "b04e0a807361ea54fd3d5d7ccf0f901a"
        const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
        const val TMDB_TOP_RATED =
            "movie/top_rated?api_key=$TMDB_API_KEY&language=en-US"
        const val TMDB_MOVIE_DETAIL =
            "movie/{movieId}?api_key=$TMDB_API_KEY&language=en-US"
    }

    companion object {
        fun getService(): MovieService {
            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val build = Retrofit.Builder()
                .client(client)
                .baseUrl(APIConstants.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return build.create(MovieService::class.java)
        }
    }
}