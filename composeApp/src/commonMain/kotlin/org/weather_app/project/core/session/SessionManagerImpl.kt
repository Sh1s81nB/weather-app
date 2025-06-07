package org.weather_app.project.core.session

import kotlinx.coroutines.runBlocking
import org.weather_app.project.core.datastore.AppDataStore
import org.weather_app.project.core.session.model.InitialScreen
import org.weather_app.project.permissions.PermissionManager

class SessionManagerImpl(
    private val permissionManager: PermissionManager,
    private val appDataStore: AppDataStore
): SessionManager {

    override val initialScreen: InitialScreen
        get() = runBlocking {
            when{
                appDataStore.isLanguageSelectionVisited().not() -> InitialScreen.Language
                permissionManager.isAnyPendingPermission() -> InitialScreen.Permission
                else -> InitialScreen.Home
            }
        }

}