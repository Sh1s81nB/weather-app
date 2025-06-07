package org.weather_app.project.localisation

import org.weather_app.project.features.language.LocaleManager
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue
import platform.Foundation.preferredLanguages

class PlatformLocaleManager : LocaleManager {

    override fun setLocale(languageCode: String) {
        val defaults = NSUserDefaults.standardUserDefaults
        defaults.setValue(listOf(languageCode), forKey = "AppleLanguages")
        defaults.synchronize()
    }

    override fun getLocale(): String {
        val languages = NSLocale.preferredLanguages
        return if (languages.isNotEmpty()) languages.first().toString() else "en"
    }
}