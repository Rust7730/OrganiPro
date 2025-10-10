package com.OrganiPro.Utils;

import com.OrganiPro.models.User;
import com.OrganiPro.utils.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    private User[] leaderboard;
    private User u1, u2, u3, u4, u5;

    @BeforeEach
    void setUp() {
        u1 = new User("Anya", 8, 1200);
        u2 = new User("Beto", 15, 2800);
        u3 = new User("Carla", 12, 1900);
        u4 = new User("David", 20, 4500);
        u5 = new User("Eva", 15, 2800);
        leaderboard = new User[]{u1, u2, u3, u4, u5};
    }

    @Test
    void testFiltrarPorNivel() {
        User[] resultado = Filter.filtrar(leaderboard, u -> u.getNivel() == 15);
        User[] esperado = {u2, u5};
        assertArrayEquals(esperado, resultado);
    }

    @Test
    void testFiltrarSinResultados() {
        User[] resultado = Filter.filtrar(leaderboard, u -> u.getPuntosExperiencia() > 5000);
        assertEquals(0, resultado.length);
    }
}
