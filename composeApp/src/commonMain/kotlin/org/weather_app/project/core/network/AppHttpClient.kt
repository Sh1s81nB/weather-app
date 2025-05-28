package org.weather_app.project.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.weather_app.project.commonconfigs.Context

class AppHttpClient(
    private val context: Context
) {
    @OptIn(ExperimentalSerializationApi::class)
    fun httpClient() = HttpClient(
    providePlatformEngine(context)
    )
    {
        defaultRequest {
            url("https://api.open-meteo.com/")
            headers {
                append("Accept", "application/json")
            }
        }
        expectSuccess = true
        install(HttpTimeout) {
            val timeout = 60000L
            requestTimeoutMillis = timeout
            connectTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value

                when (statusCode) {
                    in 300..599 -> throw HttpException(response, response.bodyAsText())
                }
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                    explicitNulls = false
                    encodeDefaults = true
                    classDiscriminator = "#class"
                }
            )
        }
        platformConfig()
    }
}



expect fun providePlatformEngine(context: Context): HttpClientEngine

expect fun HttpClientConfig<*>.platformConfig()