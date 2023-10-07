package com.example.moviesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.data.responses.MovieListResponseRemote

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@Database(
    entities = [MovieListResponseRemote.MovieResponseRemote::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}