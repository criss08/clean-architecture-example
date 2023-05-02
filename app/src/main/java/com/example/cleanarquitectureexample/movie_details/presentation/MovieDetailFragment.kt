package com.example.cleanarquitectureexample.movie_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cleanarquitectureexample.databinding.MovieDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var binding: MovieDetailFragmentBinding
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId
        viewModel.loadMovie(movieId ?: "0")
        viewModel.viewState.observe(viewLifecycleOwner) {
            updateUi(it)
        }
    }

    private fun updateUi(viewState: MovieDetailViewState) {
        when (viewState) {
            is MovieDetailViewState.Content -> {
                with(binding) {
                    binding.loadingView.isVisible = false
                    val movie = viewState.movie
                    viewMovieTitle.text = movie.title
                    Glide.with(requireContext()).load(movie.posterPath).into(viewMovieImage)
                    binding.viewShortDescription.text = movie.releaseDate
                    binding.viewFullDescription.text = movie.overview
                }
            }
            MovieDetailViewState.Error -> {
                binding.loadingView.isVisible = false
            }
            MovieDetailViewState.Loading -> {
                binding.loadingView.isVisible = true
            }
        }
    }
}