package com.pdcase.hospital.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pdcase.hospital.entities.Especialidades;

public class EspecialidadesRepositoryTest {

    @Mock
    private EspecialidadesRepository especialidadesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByIdAndNome_Encontrado() {

        Especialidades especialidadeMock = new Especialidades();
        especialidadeMock.setId(1L);
        especialidadeMock.setNome("cardiologia");

        when(especialidadesRepository.findByIdAndNome(1L, "cardiologia"))
                .thenReturn(especialidadeMock);

        Especialidades resultado = especialidadesRepository.findByIdAndNome(1L, "cardiologia");

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("cardiologia", resultado.getNome());

        verify(especialidadesRepository, times(1)).findByIdAndNome(1L, "cardiologia");
    }

    @Test
    public void testFindByIdAndNome_NaoEncontrado() {

        when(especialidadesRepository.findByIdAndNome(1L, "ortopedia"))
                .thenReturn(null);

        Especialidades resultado = especialidadesRepository.findByIdAndNome(1L, "ortopedia");

        assertNull(resultado);

        verify(especialidadesRepository, times(1)).findByIdAndNome(1L, "ortopedia");
    }
}
