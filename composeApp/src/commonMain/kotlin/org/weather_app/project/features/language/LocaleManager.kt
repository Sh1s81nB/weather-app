package org.weather_app.project.features.language

interface LocaleManager {
    fun setLocale(languageCode: String)

    fun getLocale(): String
}