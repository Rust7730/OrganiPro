package com.OrganiPro;
import com.OrganiPro.models.Task;
import com.OrganiPro.models.User;
import com.OrganiPro.models.Enums.Status;
import com.OrganiPro.models.Enums.Priority;
import com.OrganiPro.utils.*;

import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIO DE LA DEMO: FLUJO DE OPERACIONES ---");


        System.out.println("\n[ENTRADA] Arreglo de Usuarios Original:");
        User[] usuarios = {
                new User("Anya", 8, 1200),
                new User("Beto", 15, 2800),
                new User("Carla", 12, 1900),
                new User("David", 20, 4500),
                new User("Eva", 15, 2800)
        };
        System.out.println(Arrays.toString(usuarios));


        System.out.println("\n--- PROCESAMIENTO CON UTILIDADES GENERICAS ---");


        Sort.ordenar(usuarios);
        System.out.println("\n[SALIDA tras Ordenar] Leaderboard ordenado por XP:");
        System.out.println(Arrays.toString(usuarios));


        User[] top3 = Top.seleccionar(usuarios, 3);
        System.out.println("\n[SALIDA tras Seleccionar Top 3] Los 3 mejores usuarios:");
        System.out.println(Arrays.toString(top3));

        System.out.println("\n-------------------------------------------------");

        System.out.println("\n[ENTRADA] Arreglo de Tareas de la Agenda:");
        Task[] agenda = {
                new Task("Terminar reporte", Status.PENDING, Priority.HIG),
                new Task("Enviar correos", Status.COMPLETE, Priority.MEDIUM),
                new Task("Planificar reunión", Status.PENDING, Priority.MEDIUM),
                new Task("Actualizar software", Status.COMPLETE, Priority.MEDIUM)
        };
        System.out.println(Arrays.toString(agenda));

        System.out.println("\n--- PROCESAMIENTO DE TAREAS ---");

        // Operación de Agrupar y Contar
        Map<Status, Long> conteo = MultiTask.agruparYContar(agenda, Task::getEstado);
        System.out.println("\n[SALIDA tras Agrupar por Estado] Resumen de tareas:");
        System.out.println(conteo);

        System.out.println("\n--- FIN DE LA DEMO ---");
    }
}