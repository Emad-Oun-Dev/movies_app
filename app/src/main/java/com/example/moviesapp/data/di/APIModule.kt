package com.example.moviesapp.data.di

import com.example.moviesapp.data.apis.MoviesApis
import com.example.moviesapp.data.di.DaggerConstants.MOVIES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
class APIModule {

    @Singleton
    @Provides
    fun provideMoviesApi(@Named(MOVIES) retrofit: Retrofit): MoviesApis =
        retrofit.create(MoviesApis::class.java)

}