package org.weather_app.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.weather_app.project.core.data.weather.WeatherDatabaseRepository
import org.weather_app.project.features.weatherhistory.screen.WeatherHistoryUiState
import org.weather_app.project.features.weatherhistory.screen.WeatherHistoryViewModel
import org.weather_app.project.model.WeatherData
import org.weatherapp.project.database.WeatherEntityData
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class WeatherHistoryViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testWeatherHistoryUiStateSuccess() = runTest {
        val fakeData = listOf(
            WeatherEntityData(
                id = 1L,
                latitude = 10.0,
                longitude = 20.0,
                generationTimeMs = 100.0,
                utcOffsetSeconds = 0,
                timezone = "UTC",
                timezoneAbbreviation = "UTC",
                elevation = 100.0,
                temperature = "23C",
                windDirection = "220°",
                windSpeed = "22km/h",
                isDay = 0,
                weatherCode = "Clear",
                time = "2023-10-01T00:00:00Z"
            )
        )

        val fakeRepository = object : WeatherDatabaseRepository {
            override suspend fun insertWeatherData(weatherData: WeatherData) {}
            override suspend fun getWeatherData() = flowOf(fakeData)
        }

        val viewModel = WeatherHistoryViewModel(
            weatherDatabaseRepository = fakeRepository,
        )

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.first { it is WeatherHistoryUiState.Success }
        assertTrue(state is WeatherHistoryUiState.Success)
        val successState = state as WeatherHistoryUiState.Success
        assertEquals(1, successState.weatherHistory.size)
        assertEquals("23C", successState.weatherHistory[0].temperature)
    }
}