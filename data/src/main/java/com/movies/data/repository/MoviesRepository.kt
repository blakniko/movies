package com.movies.data.repository

import com.movies.commons.Either
import com.movies.data.models.MoviesResponse

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
interface MoviesRepository {
    suspend fun getAllMovies(): Either<MoviesResponse>
}