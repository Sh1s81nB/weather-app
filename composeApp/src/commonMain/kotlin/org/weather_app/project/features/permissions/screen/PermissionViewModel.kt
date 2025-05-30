package org.weather_app.project.features.permissions.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.weather_app.project.features.permissions.domain.Permission
import org.weather_app.project.permissions.PermissionCallback
import org.weather_app.project.permissions.PermissionManager

class PermissionViewModel(
    private val permissionManager: PermissionManager
): ViewModel() {

    private val _uiState = MutableStateFlow<PermissionUiState>(PermissionUiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PermissionUiState.Loading
    )

    init {
        viewModelScope.launch {
            getPendingPermissions()
            permissionManager.updatePendingPermission()
        }
    }

    private fun getPendingPermissions(){
        viewModelScope.launch {
            PermissionCallback.pendingPermissionListener.collect { pendingPermission ->
                if(pendingPermission.isEmpty()){
                    _uiState.update { PermissionUiState.OnAllPermissionsGranted }
                } else {
                    _uiState.update { PermissionUiState.PendingPermissions(pendingPermission) }
                }
            }
        }
    }

    fun acceptPendingPermissions() {
        viewModelScope.launch {
            permissionManager.acceptPendingPermission()
        }
    }
}

sealed interface PermissionUiState {
    data object Loading : PermissionUiState
    data object OnAllPermissionsGranted : PermissionUiState
    data class PendingPermissions(val pendingPermissions: List<Permission>) : PermissionUiState
}