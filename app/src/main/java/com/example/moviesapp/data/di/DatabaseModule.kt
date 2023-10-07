package com.example.moviesapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.R
import com.example.moviesapp.data.database.AppDatabase
import com.example.moviesapp.data.database.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        app.getString(R.string.database)
    ).build()

    @Provides
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao()
    }
}