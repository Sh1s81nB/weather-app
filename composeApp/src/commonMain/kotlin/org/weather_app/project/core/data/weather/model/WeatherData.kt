package org.weather_app.project.core.data.weather.model

import org.weather_app.project.core.network.model.NetworkCurrentWeather
import org.weather_app.project.core.network.model.NetworkCurrentWeatherUnits
import org.weather_app.project.core.network.model.NetworkWeatherResponse
import org.weather_app.project.model.CurrentWeather
import org.weather_app.project.model.CurrentWeatherUnits
import org.weather_app.project.model.WeatherData

fun NetworkWeatherResponse.uiModel() = WeatherData(
    latitude = latitude,
    longitude = longitude,
    generationTimeMs = generationTimeMs,
    utcOffsetSeconds = utcOffsetSeconds,
    timezone = timezone,
    timezoneAbbreviation = timezoneAbbreviation,
    elevation = elevation,
    currentWeatherUnits = currentWeatherUnits.uiModel(),
    currentWeather = currentWeather.uiModel()
)

fun NetworkCurrentWeatherUnits.uiModel() = CurrentWeatherUnits(
    time = time,
    interval = interval,
    temperature = temperature,
    windspeed = windspeed,
    winddirection = winddirection,
    isDay = isDay,
    weathercode = weathercode
)

fun NetworkCurrentWeather.uiModel() = CurrentWeather(
    time = time,
    interval = interval,
    temperature = temperature,
    windspeed = windspeed,
    winddirection = winddirection,
    isDay = isDay,
    weathercode = weathercode
)