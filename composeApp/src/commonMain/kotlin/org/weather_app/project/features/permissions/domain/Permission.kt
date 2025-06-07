package org.weather_app.project.features.permissions.domain

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.weather_app.project.permissions.PermissionType
import weather_app.composeapp.generated.resources.Res
import weather_app.composeapp.generated.resources.location
import weather_app.composeapp.generated.resources.location_description
import weather_app.composeapp.generated.resources.map
import weather_app.composeapp.generated.resources.notification
import weather_app.composeapp.generated.resources.notification_description

enum class Permission(
    val icon: DrawableResource,
    val permissionName: StringResource,
    val description: StringResource,
    val permissionType: PermissionType
) {
    LOCATION(
        icon = Res.drawable.map,
        permissionName = Res.string.location,
        description = Res.string.location_description,
        permissionType = PermissionType.LOCATION
    ),
    NOTIFICATION(
        icon = Res.drawable.notification,
        permissionName = Res.string.notification,
        description = Res.string.notification_description,
        permissionType = PermissionType.NOTIFICATION
    );
}