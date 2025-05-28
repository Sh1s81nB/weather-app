package org.weather_app.project.model

data class WeatherData(
    val latitude: Double,
    val longitude: Double,
    val generationTimeMs: Double,
    val utcOffsetSeconds: Long,
    val timezone: String,
    val timezoneAbbreviation: String,
    val elevation: Double,
    val currentWeatherUnits: CurrentWeatherUnits,
    val currentWeather: CurrentWeather
)

data class CurrentWeatherUnits(
    val time: String,
    val interval: String,
    val temperature: String,
    val windspeed: String,
    val winddirection: String,
    val isDay: String,
    val weathercode: String
)

data class CurrentWeather(
    val time: String,
    val interval: Long,
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Long,
    val isDay: Long,
    val weathercode: Long
)


