package com.pdcase.hospital.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdcase.hospital.entities.FichaPaciente;
import com.pdcase.hospital.entities.dto.FichaPacienteDTO;
import com.pdcase.hospital.exception.FichaJaExisteException;
import com.pdcase.hospital.exception.FichaNaoExisteException;
import com.pdcase.hospital.exception.ResultadoNaoEntontradoException;
import com.pdcase.hospital.repositories.FichaPacienteRepository;
import com.pdcase.hospital.services.FichaPacienteService;

@Service
public class FichaPacienteServiceImpl implements FichaPacienteService{
	
	@Autowired
	FichaPacienteRepository repository;
    @Autowired
    ModelMapper mapper;


	@Override
	public FichaPacienteDTO salvarFicha(FichaPacienteDTO dto) {
		
		FichaPaciente fichaBanco =  buscarFicha(dto);
		
		if(fichaBanco != null && !fichaBanco.equals(null)) {
			throw new FichaJaExisteException("Esta especialidade: " + dto.getEspecialidades().getNome() + " já foi utiliza para o plano: " + dto.getPlanosSaude().getNome());
		
		}else {
			fichaBanco = new FichaPaciente();
			fichaBanco = mapper.map(dto, fichaBanco.getClass());
			fichaBanco = repository.save(fichaBanco);
			return mapper.map(fichaBanco, dto.getClass());
		}
	}

	@Override
	public FichaPaciente buscarFicha(FichaPacienteDTO dto) {
		return  repository.buscarFichaExiste(dto.getNomePaciente(), dto.getNumeroCarteiraPlano(), dto.getPlanosSaude().getId(), dto.getEspecialidades().getId());
	}
	
	@Override
	public FichaPacienteDTO alterarFicha(FichaPacienteDTO dto) {
		
		FichaPaciente fichaBanco = repository.findById(dto.getId()).get();
		
		if(fichaBanco != null && !fichaBanco.equals(null)) {
			fichaBanco = mapper.map(dto, fichaBanco.getClass());
			
			List<FichaPaciente> list = repository.findByNomePacienteAndNumeroCarteiraPlano(fichaBanco.getNomePaciente(), fichaBanco.getNumeroCarteiraPlano());
			for (FichaPaciente ficha : list) {
			    if(!fichaBanco.getId().equals(ficha.getId()) && fichaBanco.getEspecialidades().equals(ficha.getEspecialidades()) && fichaBanco.getPlanosSaude().equals(ficha.getPlanosSaude())) {

			    	throw new FichaJaExisteException("Esta especialidade: " + dto.getEspecialidades().getNome() + " já foi utiliza para o plano: " + dto.getPlanosSaude().getNome());
			    }
			}
			
			fichaBanco = repository.save(fichaBanco);
			return mapper.map(fichaBanco, dto.getClass());
		}else {
			throw new FichaNaoExisteException("Error: Ficha não encontrada!");
		}
	}

	@Override
	public List<FichaPacienteDTO> buscarPorNomeECarteiraPlano(String nomePaciente, String numeroCarteiraPlano) {
		List<FichaPaciente> fichas = repository.findByNomePacienteAndNumeroCarteiraPlano(nomePaciente, numeroCarteiraPlano);
		
		if (fichas == null || fichas.isEmpty()) {
			throw new ResultadoNaoEntontradoException("Não há resultados para os parametros informados.");
        }else {
        	return fichas.stream()
                    .map(fichaPaciente -> mapper.map(fichaPaciente, FichaPacienteDTO.class))
                    .collect(Collectors.toList());
        }

	}

	@Override
	public void deletarFicha(Long id) throws Exception {
		try {
			FichaPaciente fichaBanco = repository.findById(id).get();
			 repository.delete(fichaBanco);
		} catch (Exception e) {
			throw new Exception();
		}
		
	}

}