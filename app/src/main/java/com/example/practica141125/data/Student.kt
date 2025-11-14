package com.example.practica141125.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val grado: Int,
    val grupo: String,
    val puntaje: Double
)