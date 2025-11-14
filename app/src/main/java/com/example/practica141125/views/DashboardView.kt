package com.example.practica141125.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica141125.data.Student
import com.example.practica141125.navigation.NavManager
import com.example.practica141125.navigation.Routes
import com.example.practica141125.viewModels.DashboardViewModel
import com.example.practica141125.viewmodels.ViewModelFactory

@Composable
fun DashboardView(
    navManager: NavManager,
    viewModelFactory: ViewModelFactory,
    viewModel: DashboardViewModel = viewModel(factory = viewModelFactory)
) {
    val students by viewModel.students.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navManager.navigateTo(Routes.ADD_STUDENT)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Estudiante")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
            items(students) { student ->
                StudentItem(
                    student = student,
                    onDelete = { viewModel.deleteStudent(student) },
                    onEdit = {
                        navManager.navigateToEditStudent(student.id)
                    }
                )
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, onDelete: () -> Unit, onEdit: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onEdit() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${student.nombre} ${student.apellidos}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Grado: ${student.grado} - Grupo: ${student.grupo}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Puntaje: ${student.puntaje}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}