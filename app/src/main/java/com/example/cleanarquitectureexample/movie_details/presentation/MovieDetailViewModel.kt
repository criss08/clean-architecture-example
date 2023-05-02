package com.example.cleanarquitectureexample.movie_details.presentation

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
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _viewState = MutableLiveData<MovieDetailViewState>()
    val viewState: LiveData<MovieDetailViewState>
        get() = _viewState

    fun loadMovie(movieId: String) {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(MovieDetailViewState.Loading)
            when (val result = repository.getMovieDetails(movieId)) {
                is Result.Error -> _viewState.postValue(MovieDetailViewState.Error)
                is Result.Success -> _viewState.postValue(MovieDetailViewState.Content(result.data))
            }
        }
    }
}