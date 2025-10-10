package com.OrganiPro.utils;

public class Sort {


    public static <T extends Comparable<T>> void ordenar(T[] arreglo) {
        if (arreglo == null || arreglo.length == 0) {
            return;
        }

        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - i - 1; j++) {
                if (arreglo[j].compareTo(arreglo[j + 1]) > 0) {
                    T temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }
    }
}