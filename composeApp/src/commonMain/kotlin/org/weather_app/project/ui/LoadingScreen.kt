package org.weather_app.project.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.semantics

@Composable
fun LoadingScreen(modifier: Modifier){
    Box(
        modifier = modifier.fillMaxSize().semantics(mergeDescendants = false) {}
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}