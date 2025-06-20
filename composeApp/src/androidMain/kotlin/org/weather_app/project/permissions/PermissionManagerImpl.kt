package org.weather_app.project.permissions

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.features.permissions.domain.Permission
import kotlin.time.Duration.Companion.seconds

class PermissionManagerImpl(
    private val context: Context
): PermissionManager, PermissionProvider {
    override suspend fun getPendingPermissions(): List<Permission> {
        return Permission.entries.filter {
            getPermissions(it.permissionType).any { permission ->
                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            }
        }
    }

    override suspend fun updatePendingPermission() {
        PermissionCallback.emit(getPendingPermissions())
    }

    override suspend fun acceptPendingPermission() {
        requestPermissions.emit(true)
        delay(1.seconds)
        requestPermissions.emit(false)
    }

    override suspend fun isAnyPendingPermission(): Boolean {
        return getPendingPermissions().isNotEmpty()
    }

    override fun getPermissions(permissionType: PermissionType): List<String> {
        return when (permissionType) {
            PermissionType.LOCATION -> listOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            PermissionType.NOTIFICATION -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                listOf(
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
            } else {
                emptyList()
            }
        }
    }

    companion object {
        val requestPermissions = MutableSharedFlow<Boolean>()
    }
}