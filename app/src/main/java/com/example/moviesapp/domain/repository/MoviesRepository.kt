package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.models.MovieResponseUiModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

interface MoviesRepository {
  suspend fun getMovies(pageNumber: Int): Flow<List<MovieResponseUiModel>>
  suspend fun getMovieDetails(movieId: Int): Flow<MovieResponseUiModel>
}