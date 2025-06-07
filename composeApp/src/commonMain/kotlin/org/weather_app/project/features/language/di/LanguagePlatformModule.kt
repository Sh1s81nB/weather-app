package org.weather_app.project.features.language.di

import org.koin.core.module.Module
import org.weather_app.project.commonconfigs.Context

internal expect fun languagePlatformModule(context: Context): Module