package org.weather_app.project.core.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import org.weather_app.project.commonconfigs.Context

actual fun providePlatformEngine(context: Context?): HttpClientEngine = OkHttp.create {
    val okHttpClient = OkHttpClient.Builder()
    preconfigured = okHttpClient.build()
}

actual fun HttpClientConfig<*>.platformConfig() {
}