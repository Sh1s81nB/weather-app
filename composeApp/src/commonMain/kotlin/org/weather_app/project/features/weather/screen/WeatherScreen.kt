package org.weather_app.project.features.weather.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.IconButton
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
import org.weather_app.project.ui.ErrorScreen
import org.weather_app.project.ui.LoadingScreen
import weather_app.composeapp.generated.resources.Res
import weather_app.composeapp.generated.resources.history
import weather_app.composeapp.generated.resources.weather

@Composable
fun WeatherScreenRoute(
    viewModel: WeatherScreenViewModel,
    navigateToWeatherHistory: () -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WeatherScreen(
        sync = viewModel::sync,
        uiState = uiState,
        navigateToWeatherHistory = navigateToWeatherHistory
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    sync: () -> Unit,
    uiState: WeatherScreenUiState,
    navigateToWeatherHistory: () -> Unit
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
                        Text("Weather Screen")
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = {
                                navigateToWeatherHistory()
                            }
                        ){
                            Image(
                                painter = painterResource(Res.drawable.history),
                                contentDescription = null,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            Box(
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = sync,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Refresh", style = MaterialTheme.typography.bodyLarge)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        when(uiState){
            is WeatherScreenUiState.Success -> {
                WeatherScreenContent(
                    uiState = uiState,
                    modifier = Modifier.padding(it)
                )
            }
            WeatherScreenUiState.Loading -> {
                LoadingScreen(modifier = Modifier.padding(it))
            }
            is WeatherScreenUiState.Error -> {
                ErrorScreen(
                    modifier = Modifier.padding(it),
                    error = uiState.message
                )
            }
        }
    }
}

@Composable
fun WeatherScreenContent(
    modifier: Modifier,
    uiState: WeatherScreenUiState.Success
){
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Image(
            painter = painterResource(Res.drawable.weather),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = uiState.data.temperature,
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(48.dp))
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
                        text = "Time",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.data.time,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Temperature",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.data.temperature,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Wind Speed",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.data.windSpeed,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Wind Direction",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.data.windDirection,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Weather Code",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.data.weatherCode,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}