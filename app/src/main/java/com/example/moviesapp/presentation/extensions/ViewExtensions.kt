package com.example.moviesapp.presentation.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide


val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)

fun ImageView.loadImage(context: Context, url: String) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(context).load(url).placeholder(circularProgressDrawable)
        .into(this)
}