package org.weather_app.project.features.permissions.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.weather_app.project.features.permissions.screen.PermissionScreenRoute

const val permissionRoute = "permission_route"

fun NavController.navigateToPermission(
    navOptions: NavOptions? = null,
) {
    this.navigate(permissionRoute, navOptions)
}

fun NavGraphBuilder.permissionScreen(
    onAllPermissionsGranted: () -> Unit,
    navigateToLanguageScreen: () -> Unit
) {
    composable(permissionRoute) {
        PermissionScreenRoute(
            onAllPermissionsGranted = onAllPermissionsGranted,
            navigateToLanguageScreen = navigateToLanguageScreen
        )
    }
}