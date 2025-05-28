package org.weather_app.project.core.data.di

import org.koin.dsl.module
import org.weather_app.project.core.data.weather.WeatherDatabaseRepository
import org.weather_app.project.core.data.weather.WeatherDatabaseRepositoryImpl
import org.weather_app.project.core.data.weather.WeatherRepository
import org.weather_app.project.core.data.weather.WeatherRepositoryImpl

val dataModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get()) }
    single<WeatherDatabaseRepository> {WeatherDatabaseRepositoryImpl(get())}
}