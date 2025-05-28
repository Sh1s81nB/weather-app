package org.weather_app.project.core.data.weather

import kotlinx.coroutines.flow.Flow
import org.weather_app.project.core.data.weather.model.uiModel
import org.weather_app.project.core.network.AppNetwork
import org.weather_app.project.core.network.util.ResponseHandler
import org.weather_app.project.model.WeatherData

class WeatherRepositoryImpl(
    private val appNetwork: AppNetwork,
    private val responseHandler: ResponseHandler,
    private val weatherDatabaseRepository: WeatherDatabaseRepository
): WeatherRepository {
    override suspend fun getWeatherData(): Flow<WeatherData> =
        responseHandler.handleFlowRequest {
            val weatherData = appNetwork.getWeatherData().uiModel()
            weatherDatabaseRepository.insertWeatherData(weatherData)
            weatherData
        }
}