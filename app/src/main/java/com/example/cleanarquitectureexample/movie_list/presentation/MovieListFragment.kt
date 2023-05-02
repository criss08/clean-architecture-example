package com.example.cleanarquitectureexample.movie_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cleanarquitectureexample.databinding.MovieListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private lateinit var binding: MovieListFragmentBinding
    private val viewModel: MovieListViewModel by viewModels()
    private val adapter = MovieCardListAdapter(::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMovieRecyclerView()
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            updateUI(viewState)
        }
        viewModel.loadMovieList()
    }

    private fun setupMovieRecyclerView() {
        binding.viewMovieList.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.viewMovieList.hasFixedSize()
        binding.viewMovieList.adapter = adapter
    }

    private fun updateUI(viewState: MovieListViewState) {
        when (viewState) {
            is MovieListViewState.Content -> {
                binding.viewMovieList.isVisible = true
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                adapter.setData(viewState.movieList)
            }
            MovieListViewState.Error -> {
                binding.viewMovieList.isVisible = false
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
            }
            MovieListViewState.Loading -> {
                binding.viewMovieList.isVisible = false
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }

    // parameter just to show how to retrieve data from Adapter to the fragment
    private fun onItemClicked(viewState: MovieCardViewState) {
        findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(viewState.id.toString()))
    }
}