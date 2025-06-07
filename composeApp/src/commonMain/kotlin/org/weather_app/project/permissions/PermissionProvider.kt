package org.weather_app.project.permissions

interface PermissionProvider{
    fun getPermissions(permissionType: PermissionType): List<String>
}

enum class PermissionType {
    LOCATION,
    NOTIFICATION
}