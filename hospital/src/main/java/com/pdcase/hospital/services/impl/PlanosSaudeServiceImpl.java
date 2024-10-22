package com.pdcase.hospital.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdcase.hospital.entities.PlanosSaude;
import com.pdcase.hospital.entities.dto.PlanosSaudeDTO;
import com.pdcase.hospital.repositories.PlanosSaudeRepository;
import com.pdcase.hospital.services.PlanosSaudeService;

@Service
public class PlanosSaudeServiceImpl implements PlanosSaudeService{
	
	@Autowired
	PlanosSaudeRepository repository;
	
    @Autowired
    ModelMapper mapper;
	
	@Override
	public List<PlanosSaudeDTO> buscarTodos() {
		List<PlanosSaude> list = repository.findAll();
		return list.stream().map(planosSaude -> mapper.map(planosSaude, PlanosSaudeDTO.class))
				.collect(Collectors.toList());
	}

}
