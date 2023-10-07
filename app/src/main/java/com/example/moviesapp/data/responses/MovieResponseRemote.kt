package com.example.moviesapp.data.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

class MovieListResponseRemote(
  @SerializedName("results")
  var results: List<MovieResponseRemote>?
) {
  @Entity(tableName = "movies_table")
  data class MovieResponseRemote(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?
  )
}