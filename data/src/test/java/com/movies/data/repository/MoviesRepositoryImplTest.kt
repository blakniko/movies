package com.movies.data.repository

import androidx.annotation.RawRes
import com.movies.data.api.ApiService
import com.movies.data.models.Genre
import com.movies.data.models.Movie
import com.movies.data.models.MoviesResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit


/**
 * Created by Nicolas Pino on 30,diciembre,2022
 */
class MoviesRepositoryImplTest {
    @MockK
    private lateinit var retrofit: Retrofit

    @MockK
    private lateinit var apiService: ApiService


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { retrofit.create(ApiService::class.java) } returns apiService
    }

    @Test
    fun `when is called method getAllMovies and response is successful`() = runBlocking {
        coEvery { apiService.getAllMovies() } returns MoviesResponse(
            listOf(
                Movie(
                    "1df",
                    "batman",
                    "2022",
                    "https://image.png",
                    "1.5",
                    listOf(Genre("name", "musica")),
                    "this a funny movie",
                    "200",
                    "Christopher Nolan"
                )
            ), ""
        )
        val response = apiService.getAllMovies()
        assert(response.items.isEmpty().not())
    }

    @Test
    fun `when is called method getAllMovies and response is not successful`() = runBlocking {
        coEvery { apiService.getAllMovies() } returns MoviesResponse(emptyList(), "404")

        val response = apiService.getAllMovies()
        assert(response.items.isEmpty())
        assert(response.errorMessage == "404")
    }
}