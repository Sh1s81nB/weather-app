package org.weather_app.project.core.network

import org.weather_app.project.core.network.model.NetworkWeatherResponse

interface AppNetwork {
    suspend fun getWeatherData(): NetworkWeatherResponse
}