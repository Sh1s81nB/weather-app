package org.weather_app.project.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkWeatherResponse(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double = 0.0,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Long = 0,
    val timezone: String = "",
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String = "",
    val elevation: Double = 0.0,
    @SerialName("current_weather_units")
    val currentWeatherUnits: NetworkCurrentWeatherUnits = NetworkCurrentWeatherUnits(),
    @SerialName("current_weather")
    val currentWeather: NetworkCurrentWeather = NetworkCurrentWeather()
)

@Serializable
data class NetworkCurrentWeatherUnits(
    val time: String = "",
    val interval: String = "",
    val temperature: String = "",
    val windspeed: String = "",
    val winddirection: String = "",
    @SerialName("is_day")
    val isDay: String = "",
    val weathercode: String = ""
)

@Serializable
data class NetworkCurrentWeather(
    val time: String = "",
    val interval: Long = 0,
    val temperature: Double = 0.0,
    val windspeed: Double = 0.0,
    val winddirection: Long = 0,
    @SerialName("is_day")
    val isDay: Long = 0,
    val weathercode: Long = 0
)

