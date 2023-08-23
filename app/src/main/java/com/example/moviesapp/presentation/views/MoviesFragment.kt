package com.example.moviesapp.presentation.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.domain.models.MovieResponseUiModel
import com.example.moviesapp.presentation.adapter.MoviesAdapter
import com.example.moviesapp.presentation.core.BaseFragment
import com.example.moviesapp.presentation.resource.Resource
import com.example.moviesapp.presentation.resource.ResourceState
import com.example.moviesapp.presentation.utils.EndlessRecyclerViewScrollListener
import com.example.moviesapp.presentation.utils.MOVIE_ID
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()

    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isLoaded.not()) {
            viewModel.getMoviesList(resetPageNumber = true)
        }
        setUpRecyclerView()
        handleLoadMore()
        observeData()
    }

    private fun setUpRecyclerView() {
        binding.rvMovies.adapter = moviesAdapter
        moviesAdapter.itemCallback = {
            val bundle = Bundle()
            bundle.putString(MOVIE_ID, it.id)
            findNavController().navigate(R.id.moviesDetailsFragment, bundle)
        }
    }

    private fun handleLoadMore() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.addOnScrollListener(object : EndlessRecyclerViewScrollListener(
            layoutManager
        ) {
            override fun onScroll() {
            }

            override fun onLoadMore(
                page: Int, totalItemsCount: Int, view: RecyclerView?
            ) {
                viewModel.getMoviesList()
            }

            override fun onScrollChanged(
                recyclerView: RecyclerView?, newState: Int
            ) {
            }
        })
    }

    private fun observeData() {
        viewModel.moviesListObserver.observe(
            viewLifecycleOwner, this::handleMoviesListResult
        )

        viewModel.moviesApiStatusObserver.observe(
            viewLifecycleOwner, this::handleMoviesApiStatusResult
        )
    }

    private fun handleMoviesListResult(result: List<MovieResponseUiModel>) {
        moviesAdapter.submitList(result)
    }

    private fun handleMoviesApiStatusResult(result: Resource<Boolean>) {
        when (result.state) {
            ResourceState.LOADING -> {
                showProgressDialog(show = true)
            }
            ResourceState.SUCCESS -> {
                showProgressDialog(show = false)
            }
            ResourceState.ERROR -> {
                showProgressDialog(show = false)
                Toast.makeText(requireContext(), result.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

