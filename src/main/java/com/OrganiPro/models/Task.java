package com.OrganiPro.models;

import com.OrganiPro.models.Enums.Priority;
import com.OrganiPro.models.Enums.Status;


public class Task {
    private String descripcion;
    private Status estado;
    private Priority prioridad;

    public Task(String descripcion, Status estado, Priority prioridad) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
    }

    // --- Getters ---
    public String getDescripcion() { return descripcion; }
    public Status getEstado() { return estado; }
    public Priority getPrioridad() { return prioridad; }

    @Override
    public String toString() {
        return "Tarea{" +
                "descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", prioridad=" + prioridad +
                '}';
    }
}