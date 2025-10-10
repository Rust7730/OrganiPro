package com.OrganiPro.Utils;


import com.OrganiPro.models.User;
import com.OrganiPro.utils.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTest {

    private User[] leaderboard;

    @BeforeEach
    void setUp() {
        leaderboard = new User[]{
                new User("Anya", 8, 1200),
                new User("Beto", 15, 2800),
                new User("Carla", 12, 1900)
        };
    }

    @Test
    void testBuscarEncontrado() {
        int indice = Search.buscar(leaderboard, new User("Carla", 0, 0));
        assertEquals(2, indice);
    }

    @Test
    void testBuscarNoEncontrado() {
        int indice = Search.buscar(leaderboard, new User("Zoe", 0, 0));
        assertEquals(-1, indice);
    }
}