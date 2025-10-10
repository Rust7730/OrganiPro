package com.OrganiPro.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Predicate;

public class Filter {


    public static <T> T[] filtrar(T[] arreglo, Predicate<T> condicion) {
        if (arreglo == null || arreglo.length == 0) {
            return (T[]) Array.newInstance(arreglo.getClass().getComponentType(), 0);
        }

        T[] resultadoTemp = (T[]) Array.newInstance(arreglo.getClass().getComponentType(), arreglo.length);
        int contador = 0;
        for (T elemento : arreglo) {
            if (condicion.test(elemento)) {
                resultadoTemp[contador++] = elemento;
            }
        }
        return Arrays.copyOf(resultadoTemp, contador);
    }
}