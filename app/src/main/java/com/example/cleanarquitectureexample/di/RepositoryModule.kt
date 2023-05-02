package com.example.cleanarquitectureexample.di

import com.example.cleanarquitectureexample.shared.domain.MovieRepository
import com.example.cleanarquitectureexample.shared.data.repository.api.ApiClient
import com.example.cleanarquitectureexample.shared.data.repository.api.MovieApiRepository
import com.example.cleanarquitectureexample.shared.data.repository.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesMovieService(): MovieService = ApiClient.getService()

    @Provides
    fun providesMovieRepositoryAPI(
        service: MovieService
    ): MovieApiRepository =
        MovieApiRepository(service)

    @Provides
    fun providesMovieRepository(
        movieApiRepository: MovieApiRepository
    ): MovieRepository = movieApiRepository

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}