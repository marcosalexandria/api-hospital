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

import com.pdcase.hospital.entities.PlanosSaude;

public class PlanosSaudeRepositoryTest {

    @Mock
    private PlanosSaudeRepository planosSaudeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByIdAndNome_Encontrado() {

        PlanosSaude planoMock = new PlanosSaude();
        planoMock.setId(1L);
        planoMock.setNome("hapvida");

        when(planosSaudeRepository.findByIdAndNome(1L, "hapvida"))
                .thenReturn(planoMock);

        PlanosSaude resultado = planosSaudeRepository.findByIdAndNome(1L, "hapvida");

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("hapvida", resultado.getNome());

        verify(planosSaudeRepository, times(1)).findByIdAndNome(1L, "hapvida");
    }

    @Test
    public void testFindByIdAndNome_NaoEncontrado() {

        when(planosSaudeRepository.findByIdAndNome(1L, "Plano Inexistente"))
                .thenReturn(null);

        PlanosSaude resultado = planosSaudeRepository.findByIdAndNome(1L, "Plano Inexistente");

        assertNull(resultado);

        verify(planosSaudeRepository, times(1)).findByIdAndNome(1L, "Plano Inexistente");
    }
}

