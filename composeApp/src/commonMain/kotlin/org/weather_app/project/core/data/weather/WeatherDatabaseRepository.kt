package org.weather_app.project.core.data.weather

import kotlinx.coroutines.flow.Flow
import org.weather_app.project.model.WeatherData
import org.weatherapp.project.database.WeatherEntityData

interface WeatherDatabaseRepository {
    suspend fun insertWeatherData(weatherData: WeatherData)

    suspend fun getWeatherData(): Flow<List<WeatherEntityData>>
}