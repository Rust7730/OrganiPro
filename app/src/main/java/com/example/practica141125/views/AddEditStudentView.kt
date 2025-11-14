package com.example.practica141125.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica141125.navigation.NavManager
import com.example.practica141125.viewModels.AddEditStudentViewModel
import com.example.practica141125.viewmodels.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditStudentView(
    navManager: NavManager,
    viewModelFactory: ViewModelFactory,
    studentId: Int? = null,
    viewModel: AddEditStudentViewModel = viewModel(factory = viewModelFactory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val isEditing = studentId != null

    LaunchedEffect(studentId) {
        if (isEditing) {
            viewModel.loadStudent(studentId!!)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Editar Estudiante" else "Agregar Estudiante") },
                navigationIcon = {
                    IconButton(onClick = { navManager.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveStudent()
                navManager.navigateUp()
            }) {
                Icon(Icons.Default.Check, contentDescription = "Guardar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = { viewModel.onNombreChange(it) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.apellidos,
                onValueChange = { viewModel.onApellidosChange(it) },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.grado,
                onValueChange = { viewModel.onGradoChange(it) },
                label = { Text("Grado") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.grupo,
                onValueChange = { viewModel.onGrupoChange(it) },
                label = { Text("Grupo") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.puntaje,
                onValueChange = { viewModel.onPuntajeChange(it) },
                label = { Text("Puntaje") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}