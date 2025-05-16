package org.cheva.miniprojecttodolist.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(
    val dataStore: DataStore<Preferences>
) {

    private val tokenKey = stringPreferencesKey("token")

    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[tokenKey] ?: ""
        }
    }

}