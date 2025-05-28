package org.weather_app.project.core.network

import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse

class HttpException (
    response: HttpResponse,
    responseText: String
) : ResponseException(response, responseText)