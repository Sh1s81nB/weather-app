package org.weather_app.project.core.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import org.weather_app.project.commonconfigs.Context

actual fun providePlatformEngine(context: Context): HttpClientEngine = OkHttp.create {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.addInterceptor(
        ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context, showNotification = true))
            .alwaysReadResponseBody(true)
            .build()
    )
    preconfigured = okHttpClient.build()
}

actual fun HttpClientConfig<*>.platformConfig() {
}