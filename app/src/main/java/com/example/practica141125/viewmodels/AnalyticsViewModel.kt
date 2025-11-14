package com.example.practica141125.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica141125.data.Student
import com.example.practica141125.data.StudentDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AnalyticsUiState(
    val promedioGeneral: Double = 0.0,
    val estudianteConResago: Student? = null,
    val top3Estudiantes: List<Student> = emptyList()
)

class AnalyticsViewModel(private val dao: StudentDao) : ViewModel() {

    private val _analyticsState = MutableStateFlow(AnalyticsUiState())
    val analyticsState = _analyticsState.asStateFlow()

    private val students = dao.getAllStudents()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            students.collect { studentList ->
                if (studentList.isNotEmpty()) {
                    _analyticsState.update {
                        it.copy(
                            promedioGeneral = calcularPromedio(studentList),
                            estudianteConResago = encontrarResago(studentList)
                        )
                    }
                } else {
                    _analyticsState.update { AnalyticsUiState() }
                }
            }
        }
    }

    private fun calcularPromedio(list: List<Student>): Double {
        return list.map { it.puntaje }.average()
    }

    private fun encontrarResago(list: List<Student>): Student? {
        return list.minByOrNull { it.puntaje }
    }

    fun calcularTop3PorGrupo(grupo: String) {
        val top3 = students.value
            .filter { it.grupo.equals(grupo, ignoreCase = true) }
            .sortedByDescending { it.puntaje }
            .take(3)

        _analyticsState.update { it.copy(top3Estudiantes = top3) }
    }
}