package com.OrganiPro.utils;
import java.util.Arrays;


public class Top {


    public static <T> T[] seleccionar(T[] arreglo, int n) {
        if (arreglo == null || arreglo.length == 0 || n <= 0) {
            return Arrays.copyOf(arreglo, 0);
        }
        int tamanoReal = Math.min(n, arreglo.length);
        return Arrays.copyOf(arreglo, tamanoReal);
    }
}