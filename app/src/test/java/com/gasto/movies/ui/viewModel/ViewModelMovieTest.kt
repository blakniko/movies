package com.gasto.movies.ui.viewModel

import com.movies.commons.CustomError
import com.movies.commons.Either
import com.movies.commons.Status
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


/**
 * Created by Nicolas Pino on 29,diciembre,2022
 */
class ViewModelMovieTest {

    @MockK
    private lateinit var viewModelMovie: ViewModelMovie

    @Before
    fun setup() {
        MockKAnnotations.init(this)

    }

    @Test
    fun `when is wanted an Movie with Id and response is Movie`() {
        every { viewModelMovie.getMovieById("1df") } returns Movie(
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
        val movie = viewModelMovie.getMovieById("1df")

        assert(movie != null)
        assert(movie?.id == "1df")
    }

    @Test
    fun `when is wanted an Movie with Id and response is null`() {
        every { viewModelMovie.getMovieById("2df") } returns null
        val movie = viewModelMovie.getMovieById("2df")

        assert(movie == null)
    }

    @Test
    fun `when is called loadMovies an response is not successful`() = runBlocking {
        coEvery { viewModelMovie.getAllMoviesUseCase.execute() } returns Either.onError(CustomError.ERROR_GET_MOVIES_API)
        val response = viewModelMovie.getAllMoviesUseCase.execute()

        assert(response.error == CustomError.ERROR_GET_MOVIES_API)

    }

    @Test
    fun `when is called loadMovies an response  is successful`() = runBlocking {
        coEvery { viewModelMovie.getAllMoviesUseCase.execute() } returns Either.onSuccess(
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
        val response = viewModelMovie.getAllMoviesUseCase.execute()

        assert(response.data != null)
        assert(response.status == Status.SUCCESSFUL)

    }


}