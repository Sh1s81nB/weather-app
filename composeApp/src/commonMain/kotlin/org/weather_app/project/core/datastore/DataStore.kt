package org.weather_app.project.core.datastore

import org.weather_app.project.commonconfigs.Context

expect suspend fun Context.putBoolean(key: String, value: Boolean)

expect suspend fun Context.getBoolean(key: String): Boolean?

expect suspend fun Context.putString(key: String, value: String)

expect suspend fun Context.getString(key: String): String?