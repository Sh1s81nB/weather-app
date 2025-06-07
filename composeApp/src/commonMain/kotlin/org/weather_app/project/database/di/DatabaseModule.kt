package org.weather_app.project.database.di

import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.database.AppDataBase

fun databaseModule(context: Context?) = module {
    includes(databasePlatformModule(context))
    single { AppDataBase(get()) }
}