package com.OrganiPro.utils;


public class Search {

    public static <T> int buscar(T[] arreglo, T elemento) {
        if (arreglo == null || arreglo.length == 0) {
            return -1;
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].equals(elemento)) {
                return i;
            }
        }
        return -1; // No encontrado
    }
}
