package org.weather_app.project.permissions

import org.koin.core.module.Module
import org.weather_app.project.commonconfigs.Context

internal expect fun platformCoreModule(context: Context?): Module