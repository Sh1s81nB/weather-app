package org.weather_app.project.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.koin.compose.koinInject
import org.weather_app.project.common.rememberLifecycleEvent
import org.weather_app.project.core.session.SessionManager
import org.weather_app.project.core.session.model.InitialScreen
import org.weather_app.project.features.permissions.navigation.permissionRoute
import org.weather_app.project.features.permissions.navigation.permissionScreen
import org.weather_app.project.features.permissions.screen.PermissionScreenRoute
import org.weather_app.project.features.weather.navigation.WEATHER_SCREEN_ROUTE
import org.weather_app.project.features.weather.navigation.navigateToWeatherScreen
import org.weather_app.project.features.weather.navigation.weatherScreen
import org.weather_app.project.features.weatherhistory.navigation.navigateToWeatherHistory
import org.weather_app.project.features.weatherhistory.navigation.weatherHistoryScreen
import org.weather_app.project.permissions.PermissionManager

@Composable
fun AppNavHost(
    sessionManager: SessionManager = koinInject(),
    permissionManager: PermissionManager = koinInject()
){
    val navController = rememberNavController()
    Scaffold {

        val lifecycleEvent = rememberLifecycleEvent(lifecycleEvent = Lifecycle.Event.ON_RESUME)

        HandlePendingPermissions(
            lifecycleEvent = lifecycleEvent,
            sessionManager = sessionManager,
            permissionManager = permissionManager,
            navController = navController
        )
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = when(sessionManager.initialScreen){
                InitialScreen.Permission -> permissionRoute
                else -> WEATHER_SCREEN_ROUTE
            }
        ){
            permissionScreen(
                onAllPermissionsGranted = {
                    navController.navigateToWeatherScreen(
                        navOptions = removeFromBackstack(permissionRoute)
                    )
                }
            )
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

fun removeFromBackstack(route: String) = navOptions {
    popUpTo(route) {
        inclusive = true
    }
}

@Composable
fun HandlePendingPermissions(
    lifecycleEvent: Lifecycle.Event,
    sessionManager: SessionManager,
    permissionManager: PermissionManager,
    navController: NavHostController
) {
    if (shouldRequestPermissions(
        lifecycleEvent = lifecycleEvent,
        sessionManager = sessionManager,
        permissionManager = permissionManager
    )) {
        PermissionScreenRoute(
            onAllPermissionsGranted = {
                navController.navigateToWeatherScreen(
                    navOptions = removeFromBackstack(permissionRoute)
                )
            }
        )
    }
}

private fun shouldRequestPermissions(
    lifecycleEvent: Lifecycle.Event,
    sessionManager: SessionManager,
    permissionManager: PermissionManager
): Boolean {
    return lifecycleEvent == Lifecycle.Event.ON_RESUME &&
            sessionManager.initialScreen != InitialScreen.Permission
            && permissionManager.isAnyPendingPermission()
}