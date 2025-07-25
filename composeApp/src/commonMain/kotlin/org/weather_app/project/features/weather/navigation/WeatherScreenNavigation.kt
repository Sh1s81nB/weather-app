package org.weather_app.project.features.weather.navigation

import androidx.navigation.NavController
import androidx.navigation.navDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.weather_app.project.features.weather.screen.WeatherScreenRoute

const val WEATHER_SCREEN_ROUTE = "weather_screen"

fun NavController.navigateToWeatherScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(WEATHER_SCREEN_ROUTE, navOptions)
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.weatherScreen(
    navigateToWeatherHistory: () -> Unit,
    navigateToLanguageScreen: () -> Unit
) {
    composable(
        WEATHER_SCREEN_ROUTE,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://www.weatherapp.com/weather"
            }
        )
    ) {
        WeatherScreenRoute(
            viewModel = koinViewModel(),
            navigateToWeatherHistory = navigateToWeatherHistory,
            navigateToLanguageScreen = navigateToLanguageScreen
        )
    }
}