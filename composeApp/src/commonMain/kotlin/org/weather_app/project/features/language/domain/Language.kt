package org.weather_app.project.features.language.domain

data class LanguageDetails(
    val languageCode: String,
    val languageName: String,
    val name: String
)

enum class Language(val details: LanguageDetails) {
    ENGLISH(LanguageDetails("en", "English", "English")),
    KANNADA(LanguageDetails("kn", "Kannada", "ಕನ್ನಡ"));
}