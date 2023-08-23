package com.example.moviesapp.presentation.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding
import com.example.moviesapp.domain.models.MovieResponseUiModel
import com.example.moviesapp.presentation.core.BaseFragment
import com.example.moviesapp.presentation.extensions.loadImage
import com.example.moviesapp.presentation.resource.ResourceState
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@AndroidEntryPoint
class MoviesDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()
    private val args: MoviesDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.callMovieDetailsApi(args.movieId)
        observeData()
    }

    private fun observeData() {
        viewModel.movieObserver.observe(this) { resource ->
            when (resource.state) {
                ResourceState.LOADING -> {
                    showProgressDialog(show = true)
                }
                ResourceState.SUCCESS -> {
                    showProgressDialog(show = false)
                    fillViewWithData(resource.data)
                }
                ResourceState.ERROR -> {
                    showProgressDialog(show = false)
                    Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun fillViewWithData(data: MovieResponseUiModel?) {
        binding.tvFilmDate.text = data?.releaseDate
        binding.tvTitle.text = data?.title
        binding.tvOverview.text = data?.overview
        binding.ivFilm.loadImage(
            requireContext(), BuildConfig.FILM_POSTER_BASE_URL.plus(data?.posterPath)
        )
    }
}