package org.weather_app.project.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.weather_app.project.core.network.model.NetworkWeatherResponse

class AppNetworkImpl(
    private val httpClient: HttpClient
): AppNetwork {
    override suspend fun getWeatherData(): NetworkWeatherResponse =
        httpClient.get("v1/forecast?latitude=40.71&longitude=-74.01&current_weather=true").body()
}