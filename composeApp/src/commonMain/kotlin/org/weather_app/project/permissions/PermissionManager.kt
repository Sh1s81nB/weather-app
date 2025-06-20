package org.weather_app.project.permissions

import org.weather_app.project.features.permissions.domain.Permission

interface PermissionManager {
    suspend fun getPendingPermissions(): List<Permission>
    suspend fun updatePendingPermission()
    suspend fun acceptPendingPermission()
    suspend fun isAnyPendingPermission(): Boolean
}