package com.example.practica141125.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica141125.data.Student
import com.example.practica141125.data.StudentDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DashboardViewModel(private val dao: StudentDao) : ViewModel() {

    val students = dao.getAllStudents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            dao.deleteStudent(student)
        }
    }
}