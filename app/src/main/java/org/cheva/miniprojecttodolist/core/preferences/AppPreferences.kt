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
    private val nameKey = stringPreferencesKey("name")

    suspend fun saveName(name: String) {
        dataStore.edit { prefs ->
            prefs[nameKey] = name
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[nameKey] ?: ""
        }
    }

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