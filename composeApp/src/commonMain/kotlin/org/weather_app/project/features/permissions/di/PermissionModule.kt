package org.weather_app.project.features.permissions.di

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.features.permissions.screen.PermissionViewModel
import org.weather_app.project.permissions.platformCoreModule

fun permissionModule(context: Context?): Module = module {
    viewModel { PermissionViewModel(get()) }
    includes(platformCoreModule(context))
}