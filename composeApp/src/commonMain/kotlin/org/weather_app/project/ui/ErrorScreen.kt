package org.weather_app.project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import weather_app.composeapp.generated.resources.Res
import weather_app.composeapp.generated.resources.error

@Composable
fun ErrorScreen(
    modifier: Modifier,
    error: String
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(Res.drawable.error),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Error: $error",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
            )
        }
    }
}