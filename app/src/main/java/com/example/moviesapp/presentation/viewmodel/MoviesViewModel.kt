package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.interactor.GetMovieDetailsUseCase
import com.example.moviesapp.domain.interactor.GetMoviesUseCase
import com.example.moviesapp.domain.models.MovieResponseUiModel
import com.example.moviesapp.presentation.resource.Resource
import com.example.moviesapp.presentation.resource.ResourceState
import com.example.moviesapp.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private var pageNumber = 1
    var isLoaded: Boolean = false

    val moviesApiStatusObserver = SingleLiveEvent<Resource<Boolean>>()
    val moviesListObserver = SingleLiveEvent<List<MovieResponseUiModel>>()
    val movieObserver = SingleLiveEvent<Resource<MovieResponseUiModel>>()

    fun getMoviesList(resetPageNumber: Boolean = false) {
        if (resetPageNumber) {
            pageNumber = 1
            moviesApiStatusObserver.value = Resource(ResourceState.LOADING)
        }
        viewModelScope.launch {
            getMoviesUseCase.invoke(pageNumber = pageNumber).catch {
                Timber.d("error${it}")
                moviesApiStatusObserver.value = Resource(ResourceState.ERROR, errorMessage = it.message)
            }.collect { remoteResponse ->
                Timber.d("success")
                pageNumber++
                isLoaded = true
                if (resetPageNumber) {
                    moviesApiStatusObserver.value = Resource(ResourceState.SUCCESS)
                    moviesListObserver.value = remoteResponse
                } else {
                    addNewMovies(remoteResponse)
                }
            }
        }
    }

    private fun addNewMovies(data: List<MovieResponseUiModel>) {
        val tempList = moviesListObserver.value?.toMutableList() ?: mutableListOf()
        tempList.addAll(data)
        moviesListObserver.value = tempList
    }

    fun callMovieDetailsApi(movieId: Int) {
        viewModelScope.launch {
            movieObserver.value = Resource(ResourceState.LOADING)
            getMovieDetailsUseCase.invoke(movieId = movieId).catch {
                Timber.d("error${it}")
                movieObserver.value = Resource(ResourceState.ERROR, errorMessage = it.message)
            }.collect { remoteResponse ->
                Timber.d("success")
                movieObserver.value = Resource(ResourceState.SUCCESS, remoteResponse)
            }
        }
    }

}