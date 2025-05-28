package org.weather_app.project.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val reason: String,
    val error: Boolean
)
