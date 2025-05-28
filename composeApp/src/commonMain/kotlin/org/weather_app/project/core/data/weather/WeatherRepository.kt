package org.weather_app.project.core.data.weather

import kotlinx.coroutines.flow.Flow
import org.weather_app.project.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherData(): Flow<WeatherData>
}