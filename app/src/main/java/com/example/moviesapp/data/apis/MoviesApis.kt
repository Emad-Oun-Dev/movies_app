package com.example.moviesapp.data.apis

import com.example.moviesapp.data.responses.MovieListResponseRemote
import com.example.moviesapp.data.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

interface MoviesApis {
    @GET("3/discover/movie")
    suspend fun getMoviesApp(
        @Query("api_key") apiKey: String = API_KEY, @Query("page") pageNumber: Int
    ): Response<MovieListResponseRemote>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String, @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieListResponseRemote.MovieResponseRemote>
}