package com.OrganiPro.Utils;

import com.OrganiPro.models.User;
import com.OrganiPro.utils.Top;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SelectorTopTest {

    private User[] leaderboard;

    @BeforeEach
    void setUp() {
        leaderboard = new User[]{
                new User("David", 20, 4500),
                new User("Beto", 15, 2800),
                new User("Carla", 12, 1900),
                new User("Anya", 8, 1200)
        };
    }

    @Test
    void testSeleccionarTop2() {
        User[] top2 = Top.seleccionar(leaderboard, 2);
        assertEquals(2, top2.length);
        assertEquals("David", top2[0].getNombreUsuario());
        assertEquals("Beto", top2[1].getNombreUsuario());
    }

    @Test
    void testSeleccionarMasDeLosExistentes() {
        User[] top10 = Top.seleccionar(leaderboard, 10);
        // Debe devolver solo los que hay, sin fallar
        assertEquals(4, top10.length);
        assertArrayEquals(leaderboard, top10);
    }

    @Test
    void testSeleccionarCero() {
        User[] top0 = Top.seleccionar(leaderboard, 0);
        assertEquals(0, top0.length);
    }
}