package com.example.moviesapp.data.di

import com.example.moviesapp.data.di.DaggerConstants.MOVIES
import com.example.moviesapp.data.di.DaggerConstants.MOVIES_DETAILS
import com.example.moviesapp.BuildConfig
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

  @Singleton
  @Provides fun provideGson(): Gson = Gson()

  @Singleton
  @Provides
  @Named(MOVIES)
  fun provideMoviesRetrofit(
    client: OkHttpClient,
    gson: Gson,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL_MOVIES)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()

  @Singleton
  @Provides
  @Named(MOVIES_DETAILS)
  fun provideMoviesDetailsRetrofit(
    client: OkHttpClient,
    gson: Gson,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL_MOVIE_DETAILS)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()
}