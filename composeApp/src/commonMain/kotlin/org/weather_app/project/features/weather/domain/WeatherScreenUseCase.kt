package org.weather_app.project.features.weather.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.weather_app.project.core.data.weather.WeatherRepository

class WeatherScreenUseCase(
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(): Flow<WeatherScreenData> =
        weatherRepository.getWeatherData().map { weatherData ->
            WeatherScreenData(
                time = weatherData.currentWeatherUnits.time,
                temperature = "${weatherData.currentWeather.temperature} ${weatherData.currentWeatherUnits.temperature}",
                windSpeed = "${weatherData.currentWeather.windspeed} ${weatherData.currentWeatherUnits.windspeed}",
                windDirection = "${weatherData.currentWeather.winddirection} ${weatherData.currentWeatherUnits.winddirection}",
                isDay = weatherData.currentWeather.isDay == 1L,
                weatherCode = "${weatherData.currentWeather.weathercode} ${weatherData.currentWeatherUnits.weathercode}",
            )
        }
}

data class WeatherScreenData(
    val temperature: String,
    val windDirection: String,
    val isDay: Boolean,
    val windSpeed: String,
    val weatherCode: String,
    val time: String,
)