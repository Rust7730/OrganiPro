package com.OrganiPro.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class MultiTask {


    public static <T, K> Map<K, Long> agruparYContar(T[] arreglo, Function<T, K> clasificador) {
        Map<K, Long> conteo = new HashMap<>();
        if (arreglo == null || arreglo.length == 0) {
            return conteo; // Devuelve el mapa vacío
        }

        for (T elemento : arreglo) {
            K clave = clasificador.apply(elemento);
            // .getOrDefault se asegura de que si la clave no existe, empieza en 0.
            conteo.put(clave, conteo.getOrDefault(clave, 0L) + 1);
        }

        return conteo;
    }
}