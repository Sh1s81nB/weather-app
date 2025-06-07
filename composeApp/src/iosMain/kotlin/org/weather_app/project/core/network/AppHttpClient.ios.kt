package org.weather_app.project.core.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.weather_app.project.commonconfigs.Context

actual fun providePlatformEngine(context: Context?): HttpClientEngine {
    return Darwin.create()
}

actual fun HttpClientConfig<*>.platformConfig() {
}