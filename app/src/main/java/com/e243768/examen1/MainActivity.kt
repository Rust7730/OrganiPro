package com.e243768.examen1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.e243768.examen1.navigation.AppNavigation
import com.e243768.examen1.ui.form.FormViewModel
import com.e243768.examen1.ui.settings.SettingsViewModel
import com.e243768.examen1.ui.theme.Examen1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val formViewModel: FormViewModel = hiltViewModel()

            Examen1Theme(darkTheme = settingsViewModel.isDarkMode.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        settingsViewModel = settingsViewModel,
                        formViewModel = formViewModel
                    )
                }
            }
        }
    }
}