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
        private set // Usar 'private set' es una buena práctica para controlar modificaciones desde fuera
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    init {
        // Este bloque es para recuperar un borrador no guardado, está bien
        viewModelScope.launch {
            val drafts = userPrefs.getDrafts.first()
            if (name.isEmpty()) name = drafts.first
            if (email.isEmpty()) email = drafts.second
            if (password.isEmpty()) password = drafts.third
        }
    }

    // Esta función actualiza el borrador en DataStore, está bien
    fun onFieldChange(newName: String = name, newEmail: String = email, newPass: String = password) {
        name = newName
        email = newEmail
        password = newPass
        viewModelScope.launch {
            userPrefs.saveDraft(name, email, password)
        }
    }

    fun saveUser(onSuccess: () -> Unit) {
        // Buena práctica: validar que los campos no estén vacíos antes de guardar
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            Log.w("EXAMEN_DB", "Intento de guardado fallido: Campos vacíos.")
            return
        }

        viewModelScope.launch {
            userDao.insertUser(UserEntity(name = name, email = email, password = password))
            Log.i("EXAMEN_DB", "Usuario guardado: Nombre=$name")

            // Limpiar los campos después de guardar
            val currentName = name
            val currentEmail = email
            val currentPass = password
            name = ""
            email = ""
            password = ""


        }
    }

    // --- FUNCIÓN CORREGIDA ---
    fun printUsersToLogcat() {
        viewModelScope.launch {
            try {
                // Obtenemos el valor actual del Flow usando .first()
                val users = userDao.getAllUsers().first()

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
