package com.pdcase.hospital.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pdcase.hospital.entities.FichaPaciente;

public class FichaPacienteRepositoryTest {

    @Mock
    private FichaPacienteRepository fichaPacienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByNomePacienteAndNumeroCarteiraPlano_Encontrado() {
        FichaPaciente fichaMock = new FichaPaciente();
        fichaMock.setId(1L);
        fichaMock.setNomePaciente("João da Silva");
        fichaMock.setNumeroCarteiraPlano("1234");

        when(fichaPacienteRepository.findByNomePacienteAndNumeroCarteiraPlano("João da Silva", "1234"))
                .thenReturn(List.of(fichaMock));

        List<FichaPaciente> resultado = fichaPacienteRepository.findByNomePacienteAndNumeroCarteiraPlano("João da Silva", "1234");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        FichaPaciente resultadoFicha = resultado.get(0);
        assertEquals(1L, resultadoFicha.getId());
        assertEquals("João da Silva", resultadoFicha.getNomePaciente());
        assertEquals("1234", resultadoFicha.getNumeroCarteiraPlano());

        verify(fichaPacienteRepository, times(1)).findByNomePacienteAndNumeroCarteiraPlano("João da Silva", "1234");
    }

    @Test
    public void testFindByNomePacienteAndNumeroCarteiraPlano_NaoEncontrado() {
        when(fichaPacienteRepository.findByNomePacienteAndNumeroCarteiraPlano("Maria Oliveira", "4321"))
                .thenReturn(List.of());

        List<FichaPaciente> resultado = fichaPacienteRepository.findByNomePacienteAndNumeroCarteiraPlano("Maria Oliveira", "4321");

        assertNotNull(resultado);
        assertEquals(0, resultado.size());

        verify(fichaPacienteRepository, times(1)).findByNomePacienteAndNumeroCarteiraPlano("Maria Oliveira", "4321");
    }

    @Test
    public void testBuscarFichaExiste_Encontrado() {
        FichaPaciente fichaMock = new FichaPaciente();
        fichaMock.setId(1L);
        fichaMock.setNomePaciente("João da Silva");
        fichaMock.setNumeroCarteiraPlano("1234");

        when(fichaPacienteRepository.buscarFichaExiste("João da Silva", "1234", 1L, 2L))
                .thenReturn(fichaMock);

        FichaPaciente resultado = fichaPacienteRepository.buscarFichaExiste("João da Silva", "1234", 1L, 2L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("João da Silva", resultado.getNomePaciente());
        assertEquals("1234", resultado.getNumeroCarteiraPlano());

        verify(fichaPacienteRepository, times(1)).buscarFichaExiste("João da Silva", "1234", 1L, 2L);
    }

    @Test
    public void testBuscarFichaExiste_NaoEncontrado() {
        when(fichaPacienteRepository.buscarFichaExiste("Maria Oliveira", "4321", 1L, 2L))
                .thenReturn(null);

        FichaPaciente resultado = fichaPacienteRepository.buscarFichaExiste("Maria Oliveira", "4321", 1L, 2L);

        assertNull(resultado);

        verify(fichaPacienteRepository, times(1)).buscarFichaExiste("Maria Oliveira", "4321", 1L, 2L);
    }
}