package org.weather_app.project.features.weatherhistory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.weather_app.project.features.weatherhistory.screen.WeatherHistoryScreenRoute

const val WEATHER_HISTORY_ROUTE = "weather_history"

fun NavController.navigateToWeatherHistory(
    navOptions: NavOptions? = null
) {
    this.navigate(WEATHER_HISTORY_ROUTE, navOptions)
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.weatherHistoryScreen(
    onBack: () -> Unit
) {
    composable(WEATHER_HISTORY_ROUTE) {
        WeatherHistoryScreenRoute(
            viewModel = koinViewModel(),
            onBack = onBack
        )
    }
}