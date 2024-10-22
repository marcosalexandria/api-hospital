package com.pdcase.hospital.services;

import java.util.List;

import com.pdcase.hospital.entities.dto.PlanosSaudeDTO;

public interface PlanosSaudeService {
	
	List<PlanosSaudeDTO> buscarTodos();
}
