package com.movies.domain

import com.movies.commons.CustomError
import com.movies.commons.Either
import com.movies.commons.Status
import com.movies.data.models.Genre
import com.movies.data.models.Movie
import com.movies.data.models.MoviesResponse
import com.movies.data.repository.MoviesRepository

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


/**
 * Created by Nicolas Pino on 29,diciembre,2022
 */
class GetAllMoviesUseCaseTest {

    @MockK(relaxed = true)
    private lateinit var repository: MoviesRepository

    @MockK
    private lateinit var getAllMoviesUseCase: GetAllMoviesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getAllMoviesUseCase = GetAllMoviesUseCase(repository)
    }

    @Test
    fun `when is executed method execute and response is error`() = runBlocking {
        coEvery { getAllMoviesUseCase.execute() } returns Either.onError(CustomError.ERROR_GET_MOVIES_API)

        val response = getAllMoviesUseCase.execute()

        assert(response.status == Status.ERROR)
        assert(response.data == null)
        assert(response.error == CustomError.ERROR_GET_MOVIES_API)
    }

    @Test
    fun `when is executed method execute and response is successful`() = runBlocking {
        coEvery { getAllMoviesUseCase.execute() } returns Either.onSuccess(
            MoviesResponse(
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
        )

        val response = getAllMoviesUseCase.execute()

        assert(response.status == Status.SUCCESSFUL)
        assert(response.data != null)
        assert(response.error == null)
        assert(response.data?.items?.isEmpty()!!.not())
    }


}