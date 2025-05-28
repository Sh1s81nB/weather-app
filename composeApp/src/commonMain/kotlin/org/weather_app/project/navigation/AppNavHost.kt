package org.weather_app.project.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.weather_app.project.features.weather.navigation.WEATHER_SCREEN_ROUTE
import org.weather_app.project.features.weather.navigation.weatherScreen
import org.weather_app.project.features.weatherhistory.navigation.navigateToWeatherHistory
import org.weather_app.project.features.weatherhistory.navigation.weatherHistoryScreen

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    Scaffold {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = WEATHER_SCREEN_ROUTE
        ){
            weatherScreen(
                navigateToWeatherHistory = {
                    navController.navigateToWeatherHistory()
                }
            )

            weatherHistoryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}