package com.example.moviesapp.presentation.resource

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

sealed class ResourceState {
  object LOADING : ResourceState()
  object SUCCESS : ResourceState()
  object ERROR : ResourceState()
}