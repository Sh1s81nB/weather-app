package org.weather_app.project.core.session

import org.weather_app.project.core.session.model.InitialScreen

interface SessionManager {
    val initialScreen: InitialScreen
}