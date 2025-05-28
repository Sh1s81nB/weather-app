package org.weather_app.project.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    data class Error(val exception: Throwable? = null) : AppResult<Nothing>
    data object Loading : AppResult<Nothing>
}

fun <T> Flow<T>.asAppResult(): Flow<AppResult<T>> {
    return this.map<T, AppResult<T>> {
        AppResult.Success(it)
    }.onStart {
        emit(AppResult.Loading)
    }.catch { exception ->
        emit(AppResult.Error(exception))
    }
}