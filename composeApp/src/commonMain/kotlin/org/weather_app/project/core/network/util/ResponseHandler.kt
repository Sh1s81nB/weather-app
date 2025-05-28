package org.weather_app.project.core.network.util

import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.weather_app.project.core.network.HttpException
import org.weather_app.project.core.network.model.ApiError

class ResponseHandler {

    fun <T> handleFlowRequest(block: suspend() -> T): Flow<T> = flow {
        try {
            emit(block())
        }catch (httpException: HttpException){
            throw httpExceptionHandler(httpException)
        } catch (e: Exception) {
            throw Exception("Something went wrong")
        }
    }

    private suspend fun httpExceptionHandler(httpException: HttpException) : Exception {
        try{
            val errorResponse: ApiError =
                httpException.response.body<ApiError>()
            val error = errorResponse.reason
            return Exception(error)
        } catch (_: Exception){
            return Exception("Something went wrong")
        }
    }
}