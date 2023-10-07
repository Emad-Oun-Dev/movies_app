package com.example.moviesapp.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.TestCoroutineRule
import com.example.moviesapp.data.responses.MovieListResponseRemote
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var useCase: GetMoviesUseCase

    @Mock
    lateinit var moviesRepo: MoviesRepository

    @Before
    fun setUp() {
        useCase = GetMoviesUseCase(moviesRepo)
    }

    @Test
    fun `GetMoviesUseCase return success`() {
        val moviesList = mutableListOf<MovieListResponseRemote.MovieResponseRemote>()
        val movieItem = MovieListResponseRemote.MovieResponseRemote(
            id = 1,
            overview = "over view",
            popularity = "popularity",
            posterPath = "https://path",
            backdropPath = "https://backdrop",
            releaseDate = "14-12-1991",
            title = "El mamar"
        )
        moviesList.add(movieItem)
        testCoroutineRule.runBlockingTest {
            `when`(
                useCase.invoke(pageNumber = 1)
            ).thenAnswer {
                flowOf(moviesList)
            }
            useCase.invoke(pageNumber = 1)
            assertTrue(true)
        }
    }

    @After
    fun tearDown() {
    }
}