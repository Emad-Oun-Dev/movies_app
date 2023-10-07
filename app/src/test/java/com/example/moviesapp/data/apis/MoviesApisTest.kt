package com.example.moviesapp.data.apis

import com.example.moviesapp.data.responses.MovieListResponseRemote
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

@ExperimentalCoroutinesApi
class MoviesServiceTest: ApiAbstract<MoviesApis>() {
    private lateinit var service: MoviesApis

    @Before
    fun initService() {
        service = createService(MoviesApis::class.java)
    }

    @Test
    fun fetchMoviesTest() = runBlocking {
        enqueueResponse("/MoviesResponse.json")
        val responseBody: List<MovieListResponseRemote.MovieResponseRemote> = requireNotNull(service.getMovies(pageNumber = 1).body()?.results)
        mockWebServer.takeRequest()
        assertEquals(responseBody[0].id, 968051)
    }


}