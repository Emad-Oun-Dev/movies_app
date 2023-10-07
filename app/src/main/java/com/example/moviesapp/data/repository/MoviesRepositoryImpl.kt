package com.example.moviesapp.data.repository

import com.example.moviesapp.data.apis.MoviesApis
import com.example.moviesapp.data.database.MoviesDao
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
    private val moviesApis: MoviesApis, private val moviesDao: MoviesDao
) : MoviesRepository {
    override suspend fun getMovies(pageNumber: Int): Flow<List<MovieResponseUiModel>> {
        return flow {
            val cachedMovies = moviesDao.getMovies().map { it.mapToMovieResponseUiModel() }
            emit(cachedMovies)
            if (cachedMovies.isEmpty()) {
                val remoteMovies = moviesApis.getMovies(pageNumber = pageNumber).body()?.results
                if (cachedMovies.isEmpty()) {
                    moviesDao.insertMovies(remoteMovies)
                }
                emit(remoteMovies?.map { it.mapToMovieResponseUiModel() } ?: emptyList())
            }
        }
    }

        override suspend fun getMovieDetails(movieId: Int): Flow<MovieResponseUiModel> {
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