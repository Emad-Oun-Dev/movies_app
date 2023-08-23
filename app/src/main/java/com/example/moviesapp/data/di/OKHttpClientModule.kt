package com.example.moviesapp.data.di

import android.util.Log
import com.example.moviesapp.data.utils.RequestInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
class OKHttpClientModule {

  @Singleton
  @Provides fun provideOkHttpClient(
    networkInterceptor: RequestInterceptor
  ): OkHttpClient = Builder().connectTimeout(20, SECONDS)
    .readTimeout(30, SECONDS)
    .writeTimeout(20, SECONDS)
    .retryOnConnectionFailure(true)
    .addInterceptor(
      LoggingInterceptor.Builder()
        .setLevel(Level.BASIC)
        .log(Log.VERBOSE)
        .build()
    )
    .addInterceptor(networkInterceptor)
    .build()

  @Provides
  fun provideRequestInterceptor(): RequestInterceptor {
    return RequestInterceptor()
  }
}
