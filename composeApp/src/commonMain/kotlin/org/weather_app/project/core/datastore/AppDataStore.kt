package org.weather_app.project.core.datastore

import org.weather_app.project.commonconfigs.Context

class AppDataStore(private val context: Context) {
    suspend fun saveClientName(clientName: String) {
        context.putString(DataStoreKeys.CLIENT_NAME, clientName)
    }

    suspend fun getClientName(): String? {
        return context.getString(DataStoreKeys.CLIENT_NAME)
    }

    suspend fun setLanguageSelectionVisited(isCompleted: Boolean) {
        context.putBoolean(DataStoreKeys.LANGUAGE_SELECTION_COMPLETED, isCompleted)
    }

    suspend fun isLanguageSelectionVisited(): Boolean {
        return context.getBoolean(DataStoreKeys.LANGUAGE_SELECTION_COMPLETED) ?: false
    }
}