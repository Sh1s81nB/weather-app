package org.weather_app.project.permissions

import org.koin.core.module.Module
import org.koin.dsl.module
import org.weather_app.project.commonconfigs.Context

actual fun platformCoreModule(context: Context): Module = module{
    single<PermissionManager> {
        PermissionManagerImpl(context)
    }
}