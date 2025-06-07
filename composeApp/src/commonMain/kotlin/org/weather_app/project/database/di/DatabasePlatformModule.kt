package org.weather_app.project.database.di

import org.koin.core.module.Module
import org.weather_app.project.commonconfigs.Context

internal expect fun databasePlatformModule(context: Context?): Module