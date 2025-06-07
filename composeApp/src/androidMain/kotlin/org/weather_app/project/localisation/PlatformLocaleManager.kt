package org.weather_app.project.localisation

import org.weather_app.project.commonconfigs.Context
import org.weather_app.project.features.language.LocaleManager
import java.util.Locale

class PlatformLocaleManager(
    private val context: Context
): LocaleManager {
    override fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
    }

    override fun getLocale(): String {
        return context.resources.configuration.locales.get(0).language
    }
}