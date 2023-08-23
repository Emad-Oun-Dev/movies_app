package com.example.moviesapp.presentation.resource

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

class Resource<out T> constructor(
  val state: ResourceState,
  val data: T? = null,
  val errorMessage: String? = null
)