package org.weather_app.project.features.weatherhistory.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.weather_app.project.common.AppResult
import org.weather_app.project.common.asAppResult
import org.weather_app.project.core.data.weather.WeatherDatabaseRepository
import org.weatherapp.project.database.WeatherEntityData

class WeatherHistoryViewModel(
    private val weatherDatabaseRepository: WeatherDatabaseRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<WeatherHistoryUiState>(WeatherHistoryUiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = WeatherHistoryUiState.Loading
    )

    init {
        fetchWeatherHistory()
    }

    private fun fetchWeatherHistory() {
        viewModelScope.launch {
            weatherDatabaseRepository.getWeatherData().asAppResult().map {
                when (it) {
                    is AppResult.Success -> WeatherHistoryUiState.Success(it.data)
                    is AppResult.Error -> WeatherHistoryUiState.Error(it.exception?.message ?: "Unknown error")
                    AppResult.Loading -> WeatherHistoryUiState.Loading
                }
            }.collect { state ->
                _uiState.value = state
            }
        }
    }
}

sealed class WeatherHistoryUiState{
    data class Success(val weatherHistory: List<WeatherEntityData> = emptyList()) : WeatherHistoryUiState()
    data class Error(val message: String) : WeatherHistoryUiState()
    data object Loading : WeatherHistoryUiState()
}