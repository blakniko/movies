package com.movies.data.repository

import android.util.Log
import com.movies.commons.CustomError
import com.movies.commons.Either
import com.movies.data.api.ApiService
import com.movies.data.models.MoviesResponse
import retrofit2.Retrofit

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
class MoviesRepositoryImpl( retrofit: Retrofit) : MoviesRepository {

    private val apiService = retrofit.create(ApiService::class.java)

    override suspend fun getAllMovies(): Either<MoviesResponse> {

        return try {
            val response = apiService.getAllMovies()
            Either.onSuccess(response)

        } catch (e: Exception) {
            Log.i("exception","$e")
            Either.onError(CustomError.ERROR_GET_MOVIES_API)
        }
    }
}