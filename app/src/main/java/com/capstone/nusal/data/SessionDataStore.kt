package com.capstone.nusal.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionDataStore private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveSession(token: String) {
        dataStore.edit { preferences ->
            preferences[IS_LOGIN_KEY] = true
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGIN_KEY] ?: false
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY].toString()
        }
    }

    companion object {
        val IS_LOGIN_KEY = booleanPreferencesKey("is_login")
        val TOKEN_KEY = stringPreferencesKey("user_token")
        val NAME_KEY = stringPreferencesKey("user_name")

        @Volatile
        private var INSTANCE: SessionDataStore? = null

        fun getInstance(dataStore: DataStore<Preferences>): SessionDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}