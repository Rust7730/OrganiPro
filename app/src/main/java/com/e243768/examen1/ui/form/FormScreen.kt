package com.e243768.examen1.ui.form

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon


@Composable
fun FormScreen(
    viewModel: FormViewModel,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Guardar Usuario (Persistente)")

        Spacer(Modifier.height(16.dp))

        // --- CAMPO NOMBRE (CORREGIDO) ---
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { newName -> viewModel.onFieldChange(newName = newName) }, // Llama al ViewModel para actualizar el nombre
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Icono de Nombre")
            }
        )

        Spacer(Modifier.height(8.dp))

        // --- CAMPO EMAIL (CORREGIDO) ---
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { newEmail -> viewModel.onFieldChange(newEmail = newEmail) }, // Llama al ViewModel para actualizar el email
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Icono de Email")
            }
        )

        Spacer(Modifier.height(8.dp))

        // --- CAMPO CONTRASEÑA (CORREGIDO) ---
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { newPass -> viewModel.onFieldChange(newPass = newPass) }, // Llama al ViewModel para actualizar la contraseña
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Icono de Contraseña")
            }
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.saveUser(onSuccess = { onBack() })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar en Base de Datos")
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = { viewModel.printUsersToLogcat() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver datos guardados (Logcat)")
        }
    }
}
