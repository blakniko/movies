package com.movies.domain

import com.movies.commons.Either
import com.movies.data.models.MoviesResponse
import com.movies.data.repository.MoviesRepository

/**
 * Created by Nicolas Pino on 29,diciembre,2022
 */
class GetAllMoviesUseCase(val repository: MoviesRepository) {

   suspend fun execute():Either<MoviesResponse>{
        return repository.getAllMovies()
    }
}