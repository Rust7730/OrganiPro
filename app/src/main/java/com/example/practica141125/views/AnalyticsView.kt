package com.example.practica141125.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica141125.viewmodels.AnalyticsViewModel
import com.example.practica141125.viewmodels.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsView(
    viewModelFactory: ViewModelFactory,
    viewModel: AnalyticsViewModel = viewModel(factory = viewModelFactory)
) {
    val state by viewModel.analyticsState.collectAsState()
    var grupoInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Promedio General", style = MaterialTheme.typography.titleMedium)
                Text("%.2f".format(state.promedioGeneral), style = MaterialTheme.typography.displaySmall)
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Estudiante con Mayor Resago", style = MaterialTheme.typography.titleMedium)
                val student = state.estudianteConResago
                if (student != null) {
                    Text("${student.nombre} ${student.apellidos}", style = MaterialTheme.typography.bodyLarge)
                    Text("Puntaje: ${student.puntaje}", style = MaterialTheme.typography.bodyMedium)
                } else {
                    Text("N/A", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Top 3 Estudiantes por Grupo", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = grupoInput,
                    onValueChange = { grupoInput = it },
                    label = { Text("Ingresa el grupo") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = { viewModel.calcularTop3PorGrupo(grupoInput) }) {
                    Text("Calcular Top 3")
                }

                state.top3Estudiantes.forEachIndexed { index, student ->
                    Text("${index + 1}. ${student.nombre} ${student.apellidos} (${student.puntaje})")
                }
            }
        }
    }
}