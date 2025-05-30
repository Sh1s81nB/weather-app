package org.weather_app.project.permissions

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.weather_app.project.features.permissions.domain.Permission

object PermissionCallback {
    private val _pendingPermissionListener = MutableStateFlow<List<Permission>>(emptyList())
    val pendingPermissionListener: StateFlow<List<Permission>> = _pendingPermissionListener
    suspend fun emit(granted: List<Permission>){
        _pendingPermissionListener.emit(granted)
    }
}