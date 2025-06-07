package org.weather_app.project.core.session.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.weather_app.project.core.session.SessionManager
import org.weather_app.project.core.session.SessionManagerImpl

val sessionModule: Module = module {
    single<SessionManager> {
        SessionManagerImpl(get(), get())
    }
}