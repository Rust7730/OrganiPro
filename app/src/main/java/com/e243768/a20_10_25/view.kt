package com.e243768.a20_10_25

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.e243768.a20_10_25.ui.ViewModel.Counter

@Composable
fun vista (viewModel: Counter = viewModel()) {

    Text(text = "Contador: ${viewModel.count}")

    Button(onClick = {
        viewModel.add()
    }) {
        Text("Sumar ")
    }

}