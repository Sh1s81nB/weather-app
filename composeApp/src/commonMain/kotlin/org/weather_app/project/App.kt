package org.weather_app.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.core.data.di.dataModule
import org.weather_app.project.core.network.di.networkModule
import org.weather_app.project.core.session.di.sessionModule
import org.weather_app.project.database.di.databaseModule
import org.weather_app.project.features.language.di.languageModule
import org.weather_app.project.features.permissions.di.permissionModule
import org.weather_app.project.features.weather.di.weatherModule
import org.weather_app.project.features.weatherhistory.di.weatherHistoryModule
import org.weather_app.project.navigation.AppNavHost

@Composable
@Preview
fun App(context: Context) {
    KoinApplication(
        application = {
            modules(
                dataModule,
                networkModule(context),
                weatherModule,
                databaseModule(context),
                weatherHistoryModule,
                permissionModule(context),
                sessionModule,
                languageModule(context)
            )
        }
    ){
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = MaterialTheme.typography,
            shapes = MaterialTheme.shapes
        ) {
            AppNavHost()
        }
    }
}