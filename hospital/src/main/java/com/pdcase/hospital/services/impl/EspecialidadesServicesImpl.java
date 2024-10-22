package com.pdcase.hospital.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdcase.hospital.entities.Especialidades;
import com.pdcase.hospital.entities.dto.EspecialidadesDTO;
import com.pdcase.hospital.repositories.EspecialidadesRepository;
import com.pdcase.hospital.services.EspecialidadesService;

@Service
public class EspecialidadesServicesImpl implements EspecialidadesService{
	
	@Autowired
	EspecialidadesRepository repository;
	
    @Autowired
    ModelMapper mapper;

	@Override
	public List<EspecialidadesDTO> buscarTodos() {
		List<Especialidades> list = repository.findAll();
		return list.stream()
                .map(especialidades -> mapper.map(especialidades, EspecialidadesDTO.class))
                .collect(Collectors.toList());
	}
}
