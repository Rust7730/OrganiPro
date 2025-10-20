package com.e243768.a20_10_25.ui.ViewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Counter : ViewModel() {
    var count by mutableStateOf(0)
        private set

    fun add() {
        count++
    }
}
