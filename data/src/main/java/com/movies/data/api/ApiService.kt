package com.movies.data.api

import com.movies.data.models.MoviesResponse
import retrofit2.http.GET

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
interface ApiService {

    @GET("movies.json?key=cb03b960")
    suspend fun getAllMovies():MoviesResponse
}