package org.weather_app.project.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import org.weather_app.project.commonconfigs.Context
import kotlinx.coroutines.flow.first

const val APP_DATASTORE = "app_datastore"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(APP_DATASTORE)

actual suspend fun Context.putBoolean(key: String, value: Boolean) {
    dataStore.edit {
        it[booleanPreferencesKey(key)] = value
    }
}

actual suspend fun Context.getBoolean(key: String): Boolean? {
    return dataStore.data.first()[booleanPreferencesKey(key)]
}

actual suspend fun Context.putString(key: String, value: String) {
    dataStore.edit {
        it[stringPreferencesKey(key)] = value
    }
}

actual suspend fun Context.getString(key: String): String? {
    return dataStore.data.first()[stringPreferencesKey(key)]
}