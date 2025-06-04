package org.weather_app.project.features.weather.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.weather_app.project.common.AppResult
import org.weather_app.project.common.asAppResult
import org.weather_app.project.features.weather.domain.WeatherScreenData
import org.weather_app.project.features.weather.domain.WeatherScreenUseCase

class WeatherScreenViewModel(
    private val weatherScreenUseCase: WeatherScreenUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow<WeatherScreenUiState>(WeatherScreenUiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WeatherScreenUiState.Loading
    )

    init {
        sync()
    }

    var job: Job?= null

    fun sync(){
        job = viewModelScope.launch {
//            println("WeatherScreenViewModel.sync() running on thread: ${Thread.currentThread().name}")
            weatherScreenUseCase().asAppResult().map { result ->

                when (result) {
                    is AppResult.Success -> WeatherScreenUiState.Success(result.data)
                    is AppResult.Error -> WeatherScreenUiState.Error(result.exception?.message ?: "Unknown error")
                    AppResult.Loading -> WeatherScreenUiState.Loading
                }
            }.collect{
                _uiState.value = it
            }
        }
    }
}

sealed class WeatherScreenUiState {
    data class Success(val data: WeatherScreenData) : WeatherScreenUiState()
    data object Loading : WeatherScreenUiState()
    data class Error(val message: String) : WeatherScreenUiState()
}