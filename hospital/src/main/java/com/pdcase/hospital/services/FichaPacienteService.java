package com.pdcase.hospital.services;

import java.util.List;

import com.pdcase.hospital.entities.FichaPaciente;
import com.pdcase.hospital.entities.dto.FichaPacienteDTO;

public interface FichaPacienteService {
	
	FichaPaciente buscarFicha(FichaPacienteDTO dto);
	
	FichaPacienteDTO salvarFicha(FichaPacienteDTO dto);
	
	FichaPacienteDTO alterarFicha(FichaPacienteDTO dto);
	
	List<FichaPacienteDTO> buscarPorNomeECarteiraPlano(String nomePaciente, String numeroCarteiraPlano);
	
	void deletarFicha (Long id) throws Exception;
	
}
