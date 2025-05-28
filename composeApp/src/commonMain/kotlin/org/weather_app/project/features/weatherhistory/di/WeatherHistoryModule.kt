package org.weather_app.project.features.weatherhistory.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.weather_app.project.features.weatherhistory.screen.WeatherHistoryViewModel

val weatherHistoryModule = module {
    viewModelOf(::WeatherHistoryViewModel)
}