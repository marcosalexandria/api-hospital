package com.pdcase.hospital.entities.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.pdcase.hospital.entities.Especialidades;
import com.pdcase.hospital.entities.PlanosSaude;

public class FichaPacienteDTOTest {

    @Test
    public void testFichaPacienteDTO() {
        PlanosSaude planoSaude = new PlanosSaude();
        Especialidades especialidade = new Especialidades();
        
        FichaPacienteDTO fichaPacienteDTO = new FichaPacienteDTO();
        fichaPacienteDTO.setId(1L);
        fichaPacienteDTO.setNomePaciente("João da Silva");
        fichaPacienteDTO.setNumeroCarteiraPlano("1234");
        fichaPacienteDTO.setPlanosSaude(planoSaude);
        fichaPacienteDTO.setEspecialidades(especialidade);

        assertNotNull(fichaPacienteDTO);
        assertEquals(1L, fichaPacienteDTO.getId());
        assertEquals("João da Silva", fichaPacienteDTO.getNomePaciente());
        assertEquals("1234", fichaPacienteDTO.getNumeroCarteiraPlano());
        assertEquals(planoSaude, fichaPacienteDTO.getPlanosSaude());
        assertEquals(especialidade, fichaPacienteDTO.getEspecialidades());

        fichaPacienteDTO.setNomePaciente("Maria Oliveira");
        assertEquals("Maria Oliveira", fichaPacienteDTO.getNomePaciente());

        fichaPacienteDTO.setNumeroCarteiraPlano("4321");
        assertEquals("4321", fichaPacienteDTO.getNumeroCarteiraPlano());
    }
}