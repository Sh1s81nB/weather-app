package org.weather_app.project.permissions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.weather_app.project.features.permissions.domain.Permission
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNUserNotificationCenter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PermissionManagerImpl : PermissionManager {
    private val delegate = LocationManagerDelegate()

    override suspend fun getPendingPermissions(): List<Permission> {
        return Permission.entries.filterNot { isPermissionGranted(it) }
    }

    override suspend fun updatePendingPermission() {
        PermissionCallback.emit(getPendingPermissions())
    }

    override suspend fun acceptPendingPermission() {
        val pending = getPendingPermissions()

        if (pending.any { it.permissionType == PermissionType.LOCATION }) {
            requestLocationPermission()
        }
        if (pending.any { it.permissionType == PermissionType.NOTIFICATION }) {
            requestNotificationPermission()
        }
    }

    override suspend fun isAnyPendingPermission(): Boolean {
        return getPendingPermissions().isNotEmpty()
    }

    private suspend fun requestLocationPermission() {
        suspendCoroutine<Unit> { cont ->
            delegate.requestLocationAccess {
                CoroutineScope(Dispatchers.Main).launch {
                    PermissionCallback.emit(getPendingPermissions())
                    cont.resume(Unit)
                }
            }
        }
    }

    private suspend fun requestNotificationPermission() {
        suspendCoroutine<Unit> { cont ->
            val center = UNUserNotificationCenter.currentNotificationCenter()
            center.requestAuthorizationWithOptions(
                options = UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge,
                completionHandler = { _, _ ->
                    CoroutineScope(Dispatchers.Main).launch {
                        PermissionCallback.emit(getPendingPermissions())
                        cont.resume(Unit)
                    }
                }
            )
        }
    }


    private suspend fun isPermissionGranted(permission: Permission): Boolean =
        suspendCancellableCoroutine { cont ->
            when (permission) {
                Permission.LOCATION -> {
                    val status = CLLocationManager.authorizationStatus()
                    val granted = status == kCLAuthorizationStatusAuthorizedWhenInUse || status == kCLAuthorizationStatusAuthorizedAlways
                    cont.resume(granted)
                }
                Permission.NOTIFICATION -> {
                    val center = UNUserNotificationCenter.currentNotificationCenter()
                    center.getNotificationSettingsWithCompletionHandler { settings ->
                        val granted = settings?.authorizationStatus == UNAuthorizationStatusAuthorized
                        cont.resume(granted)
                    }
                }
            }
        }
}
