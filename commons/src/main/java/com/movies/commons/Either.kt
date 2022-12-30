package com.movies.commons

/**
 * Created by Nicolas Pino on 28,diciembre,2022
 */
class Either<T>(val data: T? = null, val status: Status, val error: CustomError? = null) {

    companion object {

        fun <T> onSuccess(data: T): Either<T> {
            return Either(data, Status.SUCCESSFUL, null)
        }

        fun <T> onError(error: CustomError): Either<T> {
            return Either(status = Status.ERROR, error = error)
        }
    }
}

enum class Status {
    SUCCESSFUL,
    ERROR,
}

enum class CustomError {
    ERROR_GET_MOVIES_API
}