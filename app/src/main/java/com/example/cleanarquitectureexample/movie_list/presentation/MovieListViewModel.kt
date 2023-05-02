package com.example.cleanarquitectureexample.movie_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarquitectureexample.shared.domain.MovieRepository
import com.example.cleanarquitectureexample.shared.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _viewState = MutableLiveData<MovieListViewState>()
    val viewState: LiveData<MovieListViewState>
        get() = _viewState

    fun loadMovieList() {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(MovieListViewState.Loading)
            // Call to obtain movies
            when (val result = repository.getMovieList()) {
                is Result.Error -> _viewState.postValue(MovieListViewState.Error)
                is Result.Success -> _viewState.postValue(
                    MovieListViewState.Content(
                        result.data.map {
                            MovieCardViewState(
                                it.id,
                                it.title,
                                it.overview,
                                it.posterPath
                            )
                        }
                    ))
            }
        }
    }
}