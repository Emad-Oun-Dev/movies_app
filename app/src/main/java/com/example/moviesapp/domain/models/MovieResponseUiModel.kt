package com.example.moviesapp.domain.models

import com.example.moviesapp.data.responses.MovieListResponseRemote


/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

data class MovieResponseUiModel(
    val id: String?,
    val overview: String?,
    val popularity: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val title: String?
)

fun MovieListResponseRemote.MovieResponseRemote.mapToMovieResponseUiModel(): MovieResponseUiModel =
    MovieResponseUiModel(
        id = id,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        backdropPath = backdropPath
    )
