package com.e243768.examen1.ui.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e243768.examen1.data.datastore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPrefs: UserPreferences
) : ViewModel() {

    var isDarkMode = mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            userPrefs.getTheme.collect {
                isDarkMode.value = it
            }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            userPrefs.saveTheme(!isDarkMode.value)
        }
    }
}