package org.weather_app.project

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.koin.core.context.stopKoin
import org.weather_app.project.permissions.PermissionManagerImpl

class MainActivity : ComponentActivity() {
    private val permissionManager by lazy {
        PermissionManagerImpl(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            Box(modifier = Modifier.safeDrawingPadding()){
                App(application)
            }
            HandlePermission(permissionManager)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HandlePermission(
    permissionManager: PermissionManagerImpl
){
    val shouldRequestPermissions by PermissionManagerImpl.requestPermissions.collectAsStateWithLifecycle(
        initialValue = false
    )
    val pendingPermissions = permissionManager.getPendingPermissions()

    val permissionState = rememberMultiplePermissionsState(
        permissions = pendingPermissions.map { permissionManager.getPermissions(it.permissionType) }
            .flatten()
    )

    val context = LocalContext.current
    LaunchedEffect(shouldRequestPermissions) {
        if (shouldRequestPermissions) {
            if (permissionState.shouldShowRationale){
                context.startApplicationSettings()
            } else {
                permissionState.launchMultiplePermissionRequest()
            }
        }
    }
}

fun Context.startApplicationSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri: Uri = Uri.fromParts("package", this.packageName, null)
    intent.data = uri
    ContextCompat.startActivity(this, intent, null)
}