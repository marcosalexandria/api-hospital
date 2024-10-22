package com.pdcase.hospital.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspecialidadesTest {
	private Especialidades especialidade1;
    private Especialidades especialidade2;

    @BeforeEach
    void setUp() {
        especialidade1 = new Especialidades(1L, "Cardiologia");
        especialidade2 = new Especialidades(2L, "Neurologia");
    }

    @Test
    void testGetters() {
        assertEquals(1L, especialidade1.getId());
        assertEquals("Cardiologia", especialidade1.getNome());
    }

    @Test
    void testSetters() {
        especialidade1.setNome("Pediatria");
        assertEquals("Pediatria", especialidade1.getNome());
    }

    @Test
    void testEqualsAndHashCode() {
        Especialidades especialidadeCopy = new Especialidades(1L, "Cardiologia");
        assertEquals(especialidade1, especialidadeCopy);
        assertNotEquals(especialidade1, especialidade2);

        assertEquals(especialidade1.hashCode(), especialidadeCopy.hashCode());
        assertNotEquals(especialidade1.hashCode(), especialidade2.hashCode());
    }

    @Test
    void testNoArgsConstructor() {
        Especialidades especialidade = new Especialidades();
        especialidade.setNome("Oncologia");
        assertEquals("Oncologia", especialidade.getNome());
    }
}
