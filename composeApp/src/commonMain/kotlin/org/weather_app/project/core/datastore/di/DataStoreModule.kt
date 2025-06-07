package org.weather_app.project.core.datastore.di

import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.core.datastore.AppDataStore

fun dataStoreModule(context: Context) = module {
    single { AppDataStore(context) }
}