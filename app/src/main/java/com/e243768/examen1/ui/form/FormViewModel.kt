package com.e243768.examen1.ui.form

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e243768.examen1.data.dao.UserDao
import com.e243768.examen1.data.datastore.UserPreferences
import com.e243768.examen1.data.model.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val userDao: UserDao,
    private val userPrefs: UserPreferences
) : ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    init {
        viewModelScope.launch {
            val drafts = userPrefs.getDrafts.first()
            if (name.isEmpty()) name = drafts.first
            if (email.isEmpty()) email = drafts.second
            if (password.isEmpty()) password = drafts.third
        }
    }

    fun onFieldChange(newName: String = name, newEmail: String = email, newPass: String = password) {
        name = newName
        email = newEmail
        password = newPass
        viewModelScope.launch {
            userPrefs.saveDraft(name, email, password)
        }
    }

    fun saveUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            userDao.insertUser(UserEntity(name = name, email = email, password = password))
            name = ""
            email = ""
            password = ""
            userPrefs.clearDraft()
            onSuccess()
        }
    }

    fun printUsersToLogcat() {
        viewModelScope.launch {
            try {
                val users = userDao.getAllUsers()
                Log.d("EXAMEN_DB", "=== INICIO LISTA DE USUARIOS (${users.size}) ===")
                if (users.isEmpty()) {
                    Log.d("EXAMEN_DB", "No hay usuarios guardados todavía.")
                } else {
                    users.forEach { user ->
                        Log.d("EXAMEN_DB", " ID: ${user.id} |  Nombre: ${user.name} |  Email: ${user.email} | pass: ${user.password}")
                    }
                }
                Log.d("EXAMEN_DB", "=== FIN LISTA ===")
            } catch (e: Exception) {
                Log.e("EXAMEN_DB", "Error al obtener usuarios: ${e.message}")
            }
        }
    }
}