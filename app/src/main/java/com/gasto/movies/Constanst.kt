package com.gasto.movies

import androidx.core.net.toUri

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
object Constanst {
    const val BASE_URL = "https://my.api.mockaroo.com/"
    const val MOVIE_DETAIL_URI = "appmovies://detailmovie/"

    fun getMovieDetailUri(movieId: String) = "$MOVIE_DETAIL_URI${movieId}/".toUri()

}

object ConstansAguments{
    const val KEY_ARGUMENT_MOVIE_ID="id"
}