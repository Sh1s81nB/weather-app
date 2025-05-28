package org.weather_app.project.core.data.weather

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.weather_app.project.database.AppDataBase
import org.weather_app.project.model.WeatherData
import org.weatherapp.project.database.WeatherEntityData

class WeatherDatabaseRepositoryImpl(
    private val database: AppDataBase
): WeatherDatabaseRepository {
    override suspend fun insertWeatherData(weatherData: WeatherData) {
        withContext(Dispatchers.IO){
            database.weatherEntityDataQueries.insertWeatherData(
                latitude = weatherData.latitude,
                longitude = weatherData.longitude,
                generationTimeMs = weatherData.generationTimeMs,
                utcOffsetSeconds = weatherData.utcOffsetSeconds,
                timezone = weatherData.timezone,
                timezoneAbbreviation = weatherData.timezoneAbbreviation,
                elevation = weatherData.elevation,
                time = weatherData.currentWeatherUnits.time,
                temperature = "${weatherData.currentWeather.temperature} ${weatherData.currentWeatherUnits.temperature}",
                windSpeed = "${weatherData.currentWeather.windspeed} ${weatherData.currentWeatherUnits.windspeed}",
                windDirection = "${weatherData.currentWeather.winddirection} ${weatherData.currentWeatherUnits.winddirection}",
                isDay = weatherData.currentWeather.isDay,
                weatherCode = "${weatherData.currentWeather.weathercode} ${weatherData.currentWeatherUnits.weathercode}",
            )
        }
    }

    override suspend fun getWeatherData(): Flow<List<WeatherEntityData>> = flow {
        emit(database.weatherEntityDataQueries.getAllWeatherData().executeAsList())
    }.flowOn(Dispatchers.IO)
}