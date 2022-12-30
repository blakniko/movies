package com.gasto.movies.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.gasto.movies.ui.state.MovieState
import com.movies.commons.BaseViewModel
import com.movies.commons.Status
import com.movies.data.models.Movie
import com.movies.domain.GetAllMoviesUseCase
import kotlinx.coroutines.launch

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
class ViewModelMovie( val getAllMoviesUseCase: GetAllMoviesUseCase) : BaseViewModel() {
    private val movies = mutableListOf<Movie>()

    fun loadMovies() {
        viewModelScope.launch {
            mutableState.value = MovieState.LoadingMovies

            val response = getAllMoviesUseCase.execute()
            when (response.status) {

                Status.SUCCESSFUL -> {
                    movies.clear()
                    movies.addAll(response?.data?.items ?: emptyList())

                    mutableState.value =
                        MovieState.LoadedMovies(response.data?.items?.sortedByDescending { it.year }
                            ?: emptyList())
                }

                Status.ERROR -> {
                    mutableState.value = MovieState.ErrorLoadingMovies
                }
            }
        }
    }

    fun getMovieById(movieId: String):Movie ? {
        var movie:Movie ? = null

        movies.forEach {
            if (it.id == movieId) {
                movie = it
            }
        }
        return movie
    }
}