package com.movies.data.models

import android.widget.RemoteViews

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
data class MoviesResponse(
    val items: List<Movie>,
    val errorMessage:String
)