package com.OrganiPro.Utils;
import com.OrganiPro.models.Task;
import com.OrganiPro.models.Enums.Priority;
import com.OrganiPro.models.Enums.Status;
import com.OrganiPro.utils.MultiTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
class MultiTaskTest {

    private Task[] agenda;

    @BeforeEach
    void setUp() {
        agenda = new Task[]{
                new Task("T1", Status.PENDING, Priority.HIG),
                new Task("T2", Status.COMPLETE, Priority.MEDIUM),
                new Task("T3", Status.PENDING, Priority.LOW),
                new Task("T4", Status.COMPLETE, Priority.MEDIUM),
                new Task("T5", Status.COMPLETE, Priority.LOW)
        };
    }

    @Test
    void testAgruparYContarPorEstado() {
        Map<Status, Long> conteo = MultiTask.agruparYContar(agenda, Task::getEstado);
        assertEquals(2, conteo.get(Status.PENDING));
        assertEquals(3, conteo.get(Status.COMPLETE));
        assertEquals(null, conteo.get(Status.IN_PROGRESS)); // No hay tareas en progreso
    }

    @Test
    void testAgruparYContarPorPrioridad() {
        Map<Priority, Long> conteo = MultiTask.agruparYContar(agenda, Task::getPrioridad);
        assertEquals(2, conteo.get(Priority.HIG));
        assertEquals(2, conteo.get(Priority.MEDIUM));
        assertEquals(1, conteo.get(Priority.MEDIUM));
    }
}
