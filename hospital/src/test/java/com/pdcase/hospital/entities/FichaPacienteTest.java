package com.pdcase.hospital.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class FichaPacienteTest {

    @Test
    public void testFichaPaciente() {
        PlanosSaude planoSaude = new PlanosSaude();
        Especialidades especialidade = new Especialidades();
        
        FichaPaciente fichaPaciente = new FichaPaciente(1L, "João da Silva", "1234", planoSaude, especialidade);

        assertNotNull(fichaPaciente);
        assertEquals(1L, fichaPaciente.getId());
        assertEquals("João da Silva", fichaPaciente.getNomePaciente());
        assertEquals("1234", fichaPaciente.getNumeroCarteiraPlano());
        assertEquals(planoSaude, fichaPaciente.getPlanosSaude());
        assertEquals(especialidade, fichaPaciente.getEspecialidades());

        fichaPaciente.setNomePaciente("Maria Oliveira");
        assertEquals("Maria Oliveira", fichaPaciente.getNomePaciente());

        fichaPaciente.setNumeroCarteiraPlano("4321");
        assertEquals("4321", fichaPaciente.getNumeroCarteiraPlano());
    }
    
    @Test
    public void testHashCodeAndEquals() {
        FichaPaciente fichaPaciente1 = new FichaPaciente(1L, "João da Silva", "1234", null, null);
        FichaPaciente fichaPaciente2 = new FichaPaciente(1L, "João da Silva", "1234", null, null);
        
        assertEquals(fichaPaciente1, fichaPaciente2);
        assertEquals(fichaPaciente1.hashCode(), fichaPaciente2.hashCode());
        
        fichaPaciente2.setId(2L);
        assertNotEquals(fichaPaciente1, fichaPaciente2);
    }
}