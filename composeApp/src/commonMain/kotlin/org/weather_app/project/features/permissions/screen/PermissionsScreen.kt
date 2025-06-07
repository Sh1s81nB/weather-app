package org.weather_app.project.features.permissions.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.weather_app.project.features.permissions.domain.Permission
import org.weather_app.project.ui.LoadingScreen
import weather_app.composeapp.generated.resources.Res
import weather_app.composeapp.generated.resources.accept
import weather_app.composeapp.generated.resources.change_language
import weather_app.composeapp.generated.resources.device_permission
import weather_app.composeapp.generated.resources.device_permission_description
import weather_app.composeapp.generated.resources.permission

@Composable
fun PermissionScreenRoute(
    viewModel: PermissionViewModel = koinInject(),
    onAllPermissionsGranted: () -> Unit,
    navigateToLanguageScreen: () -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PermissionScreen(
        uiState = uiState,
        onAllPermissionsGranted = onAllPermissionsGranted,
        acceptPendingPermissions = viewModel::acceptPendingPermissions,
        navigateToLanguageScreen = navigateToLanguageScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen(
    uiState: PermissionUiState,
    onAllPermissionsGranted: () -> Unit,
    acceptPendingPermissions: () -> Unit,
    navigateToLanguageScreen: () -> Unit
) {
    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(Res.string.permission)
                        )
                    }
                },
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = acceptPendingPermissions
                ) {
                    Text(
                        text = stringResource(Res.string.accept)
                    )
                }
            }
        }
    ){
        when (uiState) {
            is PermissionUiState.Loading -> {
                LoadingScreen()
            }
            is PermissionUiState.OnAllPermissionsGranted -> {
                onAllPermissionsGranted()
            }
            is PermissionUiState.PendingPermissions -> {
                PermissionScreenContent(
                    permissions = uiState.pendingPermissions,
                    paddingValues = it,
                    navigateToLanguageScreen = navigateToLanguageScreen
                )
            }
        }
    }
}

@Composable
fun PermissionScreenContent(
    permissions: List<Permission>,
    paddingValues: PaddingValues,
    navigateToLanguageScreen: () -> Unit
){
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    onClick = {
                        navigateToLanguageScreen()
                    },
                ) {
                    Text(stringResource(Res.string.change_language))
                }
            }
        }
        item {
            Column {
                Text(
                    text = stringResource(Res.string.device_permission),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(Res.string.device_permission_description),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        items(permissions) {
            PermissionItem(it)
        }
    }
}

@Composable
fun PermissionItem(
    permission: Permission
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(permission.icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = stringResource(permission.permissionName),
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(permission.description),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}