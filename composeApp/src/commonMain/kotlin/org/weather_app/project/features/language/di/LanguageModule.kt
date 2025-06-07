package org.weather_app.project.features.language.di

import androidx.lifecycle.SavedStateHandle
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.features.language.LanguageViewModel

fun languageModule(context: Context) = module {
    includes(languagePlatformModule(context))
    viewModel { (savedStateHandle: SavedStateHandle) ->
        LanguageViewModel(get(), get(), savedStateHandle)
    }
}