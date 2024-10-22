package com.pdcase.hospital.entities;
	
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlanosSaudeTest {

    private PlanosSaude plano1;
    private PlanosSaude plano2;

    @BeforeEach
    void setUp() {
        plano1 = new PlanosSaude(1L, "Unimed");
        plano2 = new PlanosSaude(2L, "Amil");
    }

    @Test
    void testGetters() {
        assertEquals(1L, plano1.getId());
        assertEquals("Unimed", plano1.getNome());
    }

    @Test
    void testSetters() {
        plano1.setNome("Bradesco Saúde");
        assertEquals("Bradesco Saúde", plano1.getNome());
    }

    @Test
    void testEqualsAndHashCode() {
        PlanosSaude planoCopy = new PlanosSaude(1L, "Unimed");
        assertEquals(plano1, planoCopy);
        assertNotEquals(plano1, plano2);

        assertEquals(plano1.hashCode(), planoCopy.hashCode());
        assertNotEquals(plano1.hashCode(), plano2.hashCode());
    }

    @Test
    void testNoArgsConstructor() {
        PlanosSaude plano = new PlanosSaude();
        plano.setNome("SulAmérica");
        assertEquals("SulAmérica", plano.getNome());
    }
}
