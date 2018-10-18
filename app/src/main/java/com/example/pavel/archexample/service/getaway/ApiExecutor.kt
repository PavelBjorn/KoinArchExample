package com.example.pavel.archexample.service.getaway

import com.google.gson.JsonParseException
import io.reactivex.Single
import retrofit2.HttpException
import java.lang.RuntimeException

interface ApiExecutor {
    fun <R, Api> execute(apiClazz: Class<Api>, action: (Api) -> Single<R>): Single<R>
}


class ApiExecutorImpl(private val apiProvider: ApiProvider) : ApiExecutor {

    override fun <R, Api> execute(apiClazz: Class<Api>, action: (Api) -> Single<R>): Single<R> {
        return action.invoke(apiProvider.provide(apiClazz))
                .doOnError { error -> mapError(error) }
    }

    private fun mapError(error: Throwable): Single<Throwable> {
        return Single.error(
                when (error) {
                    is HttpException -> mapHttpErrors(error)
                    is JsonParseException -> ApiError.SystemError(error)
                    else -> throw RuntimeException("Unexpected non-http layer exception", error)
                }
        )
    }

    private fun mapHttpErrors(error: HttpException): Throwable {
        if (error.response() == null) {
            return ApiError.NetworkError(error)
        }
        val errorBody = error.response()?.errorBody()?.string() ?: ""

        return when (error.code()) {
            403 -> ApiError.ServerError.AuthError(errorBody)
            404 -> ApiError.ServerError.NotFoundError(errorBody)
            409 -> ApiError.ServerError.ConflictError(errorBody)
            415 -> ApiError.ServerError.UnsupportedMediaError(errorBody)
            422 -> ApiError.ServerError.Validation(errorBody)
            in 500..526 -> ApiError.SystemError(error)
            else -> ApiError.ServerError.UnknownError(errorBody)
        }
    }
}

sealed class ApiError(case: Throwable? = null) : Throwable(case) {
    class NetworkError(case: Throwable? = null) : ApiError(case)
    class SystemError(case: Throwable? = null) : ApiError(case)
    sealed class ServerError(cause: Throwable? = null) : ApiError(cause) {
        class UnknownError(val description: String) : ServerError()
        class AuthError(val description: String) : ServerError()
        class Validation(val description: String) : ServerError()
        class NotFoundError(val description: String) : ServerError()
        class ConflictError(val description: String) : ServerError()
        class UnsupportedMediaError(val description: String) : ServerError()
    }
}