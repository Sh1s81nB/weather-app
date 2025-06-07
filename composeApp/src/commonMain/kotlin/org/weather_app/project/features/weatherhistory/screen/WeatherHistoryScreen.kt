package org.weather_app.project.features.weatherhistory.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.weather_app.project.ui.ErrorScreen
import org.weather_app.project.ui.LoadingScreen
import org.weatherapp.project.database.WeatherEntityData
import weather_app.composeapp.generated.resources.Res
import weather_app.composeapp.generated.resources.back
import weather_app.composeapp.generated.resources.temperature
import weather_app.composeapp.generated.resources.time
import weather_app.composeapp.generated.resources.weather_history
import weather_app.composeapp.generated.resources.wind_speed

@Composable
fun WeatherHistoryScreenRoute(
    viewModel: WeatherHistoryViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WeatherHistoryScreen(
        uiState = uiState,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherHistoryScreen(
    uiState: WeatherHistoryUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.weather_history),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        when(uiState){
            is WeatherHistoryUiState.Error -> {
                ErrorScreen(error = uiState.message, modifier = Modifier.padding(paddingValues))
            }
            WeatherHistoryUiState.Loading -> {
                LoadingScreen(modifier = Modifier.padding(paddingValues))
            }
            is WeatherHistoryUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(uiState.weatherHistory){
                        WeatherHistoryContent(
                            weatherEntityData = it,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherHistoryContent(
    weatherEntityData: WeatherEntityData,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(Res.string.time),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = weatherEntityData.time,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(Res.string.temperature),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = weatherEntityData.temperature,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}