package org.weather_app.project.database.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.database.DriverFactory

internal actual fun databasePlatformModule(context: Context?): Module = module {
    single<SqlDriver> { DriverFactory().createDriver() }
}