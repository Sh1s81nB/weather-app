package org.weather_app.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform