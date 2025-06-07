package org.weather_app.project.permissions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.weather_app.project.features.permissions.domain.Permission
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined

class PermissionManagerImpl : PermissionManager {
    private val delegate = LocationManagerDelegate()

    override fun getPendingPermissions(): List<Permission> {
        val status = CLLocationManager.authorizationStatus()
        return if (status == kCLAuthorizationStatusNotDetermined || status == kCLAuthorizationStatusDenied) {
            listOf(Permission.LOCATION)
        } else {
            emptyList()
        }
    }

    override suspend fun updatePendingPermission() {
        PermissionCallback.emit(getPendingPermissions())
    }

    override suspend fun acceptPendingPermission() {
        val pending = getPendingPermissions()

        if (pending.any { it.permissionType == PermissionType.LOCATION }) {
            requestLocationPermission()
        }
    }

    override fun isAnyPendingPermission(): Boolean {
        return getPendingPermissions().isNotEmpty()
    }

    private fun requestLocationPermission() {
        delegate.requestLocationAccess { status ->
            CoroutineScope(Dispatchers.Main).launch {
                PermissionCallback.emit(getPendingPermissions())
            }
        }
    }
}
