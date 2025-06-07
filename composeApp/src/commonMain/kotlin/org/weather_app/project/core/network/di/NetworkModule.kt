package org.weather_app.project.core.network.di

import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.core.network.AppHttpClient
import org.weather_app.project.core.network.AppNetwork
import org.weather_app.project.core.network.AppNetworkImpl
import org.weather_app.project.core.network.util.ResponseHandler

fun networkModule(context: Context) = module {
    single { AppHttpClient(context).httpClient() }
    single<AppNetwork> { AppNetworkImpl(get()) }
    single { ResponseHandler() }
}