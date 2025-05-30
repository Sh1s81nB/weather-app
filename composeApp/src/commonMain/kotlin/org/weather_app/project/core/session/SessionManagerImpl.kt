package org.weather_app.project.core.session

import kotlinx.coroutines.runBlocking
import org.weather_app.project.core.session.model.InitialScreen
import org.weather_app.project.permissions.PermissionManager

class SessionManagerImpl(
    private val permissionManager: PermissionManager
): SessionManager {

    override val initialScreen: InitialScreen
        get() = runBlocking {
            when{
                permissionManager.isAnyPendingPermission() -> InitialScreen.Permission
                else -> InitialScreen.Home
            }
        }

}