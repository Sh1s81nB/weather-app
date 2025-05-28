package org.weather_app.project.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.weather_app.project.commonconfigs.Context

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDataBase.Schema, context, "test.db")
    }
}