package com.example.moviesapp.data.database

import androidx.room.*
import com.example.moviesapp.data.responses.MovieListResponseRemote

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieListResponseRemote.MovieResponseRemote>?)

    @Query("SELECT * FROM movies_table")
    suspend fun getMovies(): List<MovieListResponseRemote.MovieResponseRemote>

}