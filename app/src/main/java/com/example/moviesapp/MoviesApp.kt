package com.example.moviesapp

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@HiltAndroidApp
class MoviesApp : Application() {

  override fun onCreate() {
    super.onCreate()
    setupTimber()
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) Timber.plant(DebugTree())
  }
}