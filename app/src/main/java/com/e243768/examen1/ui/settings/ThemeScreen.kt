package com.e243768.examen1.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemeScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Configuración de Tema", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Modo Oscuro")
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = viewModel.isDarkMode.value,
                onCheckedChange = { viewModel.toggleTheme() }
            )
        }
        Spacer(Modifier.height(32.dp))
        Button(onClick = onBack) { Text("Volver") }
    }
}