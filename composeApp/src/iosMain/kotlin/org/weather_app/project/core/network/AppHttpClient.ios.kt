package org.weather_app.project.core.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import org.weather_app.project.commonconfigs.Context

actual fun providePlatformEngine(context: Context): HttpClientEngine {
    TODO("Not yet implemented")
}

actual fun HttpClientConfig<*>.platformConfig() {
}