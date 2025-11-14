package com.example.practica141125.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica141125.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StudentUiState(
    val id: Int = 0,
    val nombre: String = "",
    val apellidos: String = "",
    val grado: String = "",
    val grupo: String = "",
    val puntaje: String = ""
)

class AddEditStudentViewModel(private val dao: StudentDao) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentUiState())
    val uiState = _uiState.asStateFlow()

    fun loadStudent(studentId: Int) {
        viewModelScope.launch {
            dao.getStudentById(studentId).firstOrNull()?.let { student ->
                _uiState.update {
                    it.copy(
                        id = student.id,
                        nombre = student.nombre,
                        apellidos = student.apellidos,
                        grado = student.grado.toString(),
                        grupo = student.grupo,
                        puntaje = student.puntaje.toString()
                    )
                }
            }
        }
    }

    fun saveStudent() {
        viewModelScope.launch {
            val currentState = _uiState.value
            val student = Student(
                id = currentState.id,
                nombre = currentState.nombre,
                apellidos = currentState.apellidos,
                grado = currentState.grado.toIntOrNull() ?: 0,
                grupo = currentState.grupo,
                puntaje = currentState.puntaje.toDoubleOrNull() ?: 0.0
            )

            if (student.id == 0) {
                dao.insertStudent(student)
            } else {
                dao.updateStudent(student)
            }
        }
    }

    fun onNombreChange(nombre: String) { _uiState.update { it.copy(nombre = nombre) } }
    fun onApellidosChange(apellidos: String) { _uiState.update { it.copy(apellidos = apellidos) } }
    fun onGradoChange(grado: String) { _uiState.update { it.copy(grado = grado) } }
    fun onGrupoChange(grupo: String) { _uiState.update { it.copy(grupo = grupo) } }
    fun onPuntajeChange(puntaje: String) { _uiState.update { it.copy(puntaje = puntaje) } }
}