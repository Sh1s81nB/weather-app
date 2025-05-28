package org.weather_app.project.features.weather.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.weather_app.project.features.weather.screen.WeatherScreenViewModel
import org.weather_app.project.features.weather.domain.WeatherScreenUseCase

val weatherModule = module {
    viewModelOf(::WeatherScreenViewModel)
    singleOf(::WeatherScreenUseCase)
}