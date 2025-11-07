package com.e243768.examen1.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences @Inject constructor(private val context: Context) {

    companion object {
        val THEME_KEY = booleanPreferencesKey("dark_mode")
        val DRAFT_NAME = stringPreferencesKey("draft_name")
        val DRAFT_EMAIL = stringPreferencesKey("draft_email")
        val DRAFT_PASS = stringPreferencesKey("draft_pass")
    }

    val getTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[THEME_KEY] ?: false }

    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { it[THEME_KEY] = isDark }
    }

    val getDrafts: Flow<Triple<String, String, String>> = context.dataStore.data
        .map { preferences ->
            Triple(
                preferences[DRAFT_NAME] ?: "",
                preferences[DRAFT_EMAIL] ?: "",
                preferences[DRAFT_PASS] ?: ""
            )
        }

    suspend fun saveDraft(name: String, email: String, pass: String) {
        context.dataStore.edit { preferences ->
            preferences[DRAFT_NAME] = name
            preferences[DRAFT_EMAIL] = email
            preferences[DRAFT_PASS] = pass
        }
    }

    suspend fun clearDraft() {
        context.dataStore.edit {
            it.remove(DRAFT_NAME)
            it.remove(DRAFT_EMAIL)
            it.remove(DRAFT_PASS)
        }
    }
}