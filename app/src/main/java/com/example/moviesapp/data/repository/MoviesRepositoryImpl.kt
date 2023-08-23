package com.example.moviesapp.data.repository

import com.example.moviesapp.data.apis.MoviesApis
import com.example.moviesapp.domain.models.MovieResponseUiModel
import com.example.moviesapp.domain.models.mapToMovieResponseUiModel
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApis: MoviesApis
) : MoviesRepository {

    override suspend fun getMovies(pageNumber: Int): Flow<List<MovieResponseUiModel>> {
        return flow {
            val response = moviesApis.getMoviesApp(pageNumber = pageNumber)
            if (response.isSuccessful) {
                response.body()?.results?.map { it.mapToMovieResponseUiModel() }?.let { emit(it) }
            } else {
                error(response)
            }
        }
    }

    override suspend fun getMovieDetails(movieId: String): Flow<MovieResponseUiModel> {
        return flow {
            val response = moviesApis.getMovieDetails(movieId = movieId)
            if (response.isSuccessful) {
                response.body()?.mapToMovieResponseUiModel()?.let { emit(it) }
            } else {
                error(response)
            }
        }
    }
}