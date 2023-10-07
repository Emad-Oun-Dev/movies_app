package com.example.moviesapp.presentaion.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviesapp.TestCoroutineRule
import com.example.moviesapp.data.responses.MovieListResponseRemote
import com.example.moviesapp.domain.interactor.GetMovieDetailsUseCase
import com.example.moviesapp.domain.interactor.GetMoviesUseCase
import com.example.moviesapp.domain.models.MovieResponseUiModel
import com.example.moviesapp.domain.models.mapToMovieResponseUiModel
import com.example.moviesapp.presentation.viewmodel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    @Mock
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Mock
    private lateinit var moviesObserver: Observer<List<MovieResponseUiModel>>

    private val moviesListRemote = mutableListOf<MovieListResponseRemote.MovieResponseRemote>()
    private val movieItemRemote =  MovieListResponseRemote.MovieResponseRemote(
        title = "Ad Astra",
        id = 419704,
        releaseDate = "2019-09-17",
        backdropPath = null,
        popularity = null,
        posterPath = null,
        overview = null
    )


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesListRemote.add(movieItemRemote)
    }

    @Test
    fun fetchMoviesListTestSuccess() {
        val moviesViewModel = MoviesViewModel(getMoviesUseCase, getMovieDetailsUseCase)
        testCoroutineRule.runBlockingTest {
            moviesViewModel.moviesListObserver.observeForever(moviesObserver)
            Mockito.`when`(getMoviesUseCase.invoke(pageNumber = 1)).thenAnswer {
                flowOf(moviesListRemote.map { it.mapToMovieResponseUiModel() })
            }
            moviesViewModel.getMoviesList(resetPageNumber = true)
            Assert.assertNotNull(moviesViewModel.moviesListObserver.value)
            assertEquals(
                moviesListRemote.map { it.mapToMovieResponseUiModel() },
                moviesViewModel.moviesListObserver.value
            )
        }
    }
}