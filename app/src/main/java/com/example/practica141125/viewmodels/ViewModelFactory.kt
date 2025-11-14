package com.example.practica141125.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practica141125.data.StudentDao
import com.example.practica141125.viewModels.*

class ViewModelFactory(private val studentDao: StudentDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AnalyticsViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                AnalyticsViewModel(studentDao) as T
            }
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                DashboardViewModel(studentDao) as T
            }
            modelClass.isAssignableFrom(AddEditStudentViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                AddEditStudentViewModel(studentDao) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}