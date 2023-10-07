package com.example.moviesapp.domain.interactor

import com.example.moviesapp.domain.models.MovieResponseUiModel
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend fun invoke(movieId: Int): Flow<MovieResponseUiModel> =
        moviesRepository.getMovieDetails(movieId = movieId)
}