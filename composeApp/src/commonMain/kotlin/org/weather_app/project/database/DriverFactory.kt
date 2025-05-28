package org.weather_app.project.database

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): AppDataBase {
    val driver = driverFactory.createDriver()
    return AppDataBase(driver)
}