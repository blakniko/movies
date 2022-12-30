package com.gasto.movies.ui.state

import com.movies.commons.BaseState
import com.movies.data.models.Movie

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
sealed class MovieState:BaseState{
    object LoadingMovies:MovieState()
    class LoadedMovies(val movies:List<Movie>):MovieState()
    object ErrorLoadingMovies:MovieState()
}
