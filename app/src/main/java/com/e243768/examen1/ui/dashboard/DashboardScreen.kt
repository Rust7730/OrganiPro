package com.e243768.examen1.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    onNavigateToTheme: () -> Unit,
    onNavigateToForm: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dashboard Principal")
        Spacer(Modifier.height(32.dp))
        Button(onClick = onNavigateToTheme) { Text("Ir a Configuración de Tema") }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onNavigateToForm) { Text("Ir a Formulario (Base de Datos)") }
    }
}