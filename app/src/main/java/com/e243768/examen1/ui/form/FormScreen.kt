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

        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.onFieldChange(newName = it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.onFieldChange(newEmail = it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.onFieldChange(newPass = it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
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