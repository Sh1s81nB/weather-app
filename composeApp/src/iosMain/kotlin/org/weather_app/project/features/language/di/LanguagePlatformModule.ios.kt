package org.weather_app.project.features.language.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.features.language.LocaleManager
import org.weather_app.project.localisation.PlatformLocaleManager

actual fun languagePlatformModule(context: Context): Module = module {
    single<LocaleManager> { PlatformLocaleManager() }
}