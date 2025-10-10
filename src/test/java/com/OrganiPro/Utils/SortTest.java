package com.OrganiPro.Utils;

import com.OrganiPro.models.User;
import com.OrganiPro.utils.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SortTest {

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
    void testOrdenarTipico() {
        Sort.ordenar(leaderboard);
        User[] esperado = {u4, u2, u5, u3, u1};
        assertArrayEquals(esperado, leaderboard);
    }

    @Test
    void testOrdenarArregloVacio() {
        User[] arregloVacio = new User[0];
        assertDoesNotThrow(() -> Sort.ordenar(arregloVacio));
    }
}