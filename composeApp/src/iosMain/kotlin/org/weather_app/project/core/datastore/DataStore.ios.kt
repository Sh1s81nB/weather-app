package org.weather_app.project.core.datastore

import org.weather_app.project.commonconfigs.Context
import platform.Foundation.NSUserDefaults

actual suspend fun Context.putBoolean(key: String, value: Boolean) {
    val defaults = NSUserDefaults.standardUserDefaults
    defaults.setBool(value, key)
}

actual suspend fun Context.getBoolean(key: String): Boolean? {
    val defaults = NSUserDefaults.standardUserDefaults
    return if (defaults.objectForKey(key) != null) {
        defaults.boolForKey(key)
    } else null
}

actual suspend fun Context.putString(key: String, value: String) {
    val defaults = NSUserDefaults.standardUserDefaults
    defaults.setObject(value, key)
}

actual suspend fun Context.getString(key: String): String? {
    val defaults = NSUserDefaults.standardUserDefaults
    return defaults.stringForKey(key)
}
