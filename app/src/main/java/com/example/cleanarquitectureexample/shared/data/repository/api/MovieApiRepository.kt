package com.example.cleanarquitectureexample.shared.data.repository.api

import android.util.Log
import com.example.cleanarquitectureexample.movie_details.domain.MovieDetail
import com.example.cleanarquitectureexample.movie_list.domain.Movie
import com.example.cleanarquitectureexample.shared.domain.MovieRepository
import com.example.cleanarquitectureexample.shared.domain.Result
import com.example.cleanarquitectureexample.shared.domain.Result.*
import com.example.cleanarquitectureexample.movie_list.domain.toDomain
import com.example.cleanarquitectureexample.movie_details.domain.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieApiRepository @Inject constructor(private val service: MovieService) :
    MovieRepository {

    override suspend fun getMovieList(): Result<List<Movie>>{
        return withContext(Dispatchers.IO) {
            try {
                val data = service.getMovieList().results.map {
                    it.toDomain()
                }
                if (data.isNotEmpty()) {
                    Success(data)
                } else {
                    Error(IllegalStateException("Empty movie list"))
                }
            } catch (exception: Exception) {
                Log.e("NetworkLayer", exception.message, exception)
                Error(exception)
            }
        }
    }

    override suspend fun getMovieDetails(movieId: String): Result<MovieDetail> {
        return withContext(Dispatchers.IO) {
            try {
                service.getMovieDetails(movieId).run {
                    Success(
                        this.toDomain()
                    )
                }
            } catch (exception: Exception) {
                Log.e("NetworkLayer", exception.message, exception)
                Error(exception)
            }
        }
    }
}