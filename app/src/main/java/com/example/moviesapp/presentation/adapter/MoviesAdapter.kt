package com.example.moviesapp.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.moviesapp.presentation.extensions.layoutInflater
import com.example.moviesapp.domain.models.MovieResponseUiModel
import com.example.moviesapp.presentation.extensions.loadImage

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

class MoviesAdapter :
    ListAdapter<MovieResponseUiModel, RecyclerView.ViewHolder>(diffUtilItemCallback) {

    var itemCallback: ((MovieResponseUiModel) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        val itemView = ItemMovieBinding.inflate(parent.layoutInflater, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int
    ) {
        getItem(position)?.let { (holder as ViewHolder).bindItem(it) }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(movieModel: MovieResponseUiModel) = with(binding) {
            tvTitle.text = movieModel.title
            tvFilmDate.text = movieModel.releaseDate
            ivFilm.loadImage(
                itemView.context, BuildConfig.FILM_POSTER_BASE_URL.plus(movieModel.posterPath)
            )
            itemView.setOnClickListener {
                itemCallback?.invoke(movieModel)
            }
        }
    }

    companion object {
        val diffUtilItemCallback = object : DiffUtil.ItemCallback<MovieResponseUiModel>() {
            override fun areItemsTheSame(
                oldItem: MovieResponseUiModel, newItem: MovieResponseUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieResponseUiModel, newItem: MovieResponseUiModel
            ): Boolean = oldItem == newItem
        }
    }
}