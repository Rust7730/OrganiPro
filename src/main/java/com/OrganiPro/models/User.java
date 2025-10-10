package com.OrganiPro.models;


public class User implements Comparable<User> {
    private String nombreUsuario;
    private int nivel;
    private int puntosExperiencia;

    public User(String nombreUsuario, int nivel, int puntosExperiencia) {
        this.nombreUsuario = nombreUsuario;
        this.nivel = nivel;
        this.puntosExperiencia = puntosExperiencia;
    }

    //  Getters
    public String getNombreUsuario() { return nombreUsuario; }
    public int getNivel() { return nivel; }
    public int getPuntosExperiencia() { return puntosExperiencia; }


    @Override
    public int compareTo(User otro) {
        return Integer.compare(otro.puntosExperiencia, this.puntosExperiencia);
    }

    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombreUsuario + '\'' +
                ", nivel=" + nivel +
                ", xp=" + puntosExperiencia +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User User = (User) obj;
        return nombreUsuario.equals(User.nombreUsuario);
    }
}