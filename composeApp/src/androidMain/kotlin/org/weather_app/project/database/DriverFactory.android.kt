package org.weather_app.project.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.weather_app.project.commonconfigs.Context

actual class DriverFactory(private val context: Context?  = null) {
    actual fun createDriver(): SqlDriver {
        requireNotNull(context) { "Context must not be null on Android" }
        return AndroidSqliteDriver(AppDataBase.Schema, context, "test.db")
    }
}