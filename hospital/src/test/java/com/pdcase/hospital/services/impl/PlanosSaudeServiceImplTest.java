package com.pdcase.hospital.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.pdcase.hospital.entities.PlanosSaude;
import com.pdcase.hospital.entities.dto.PlanosSaudeDTO;
import com.pdcase.hospital.repositories.PlanosSaudeRepository;

public class PlanosSaudeServiceImplTest {

	@InjectMocks
	private PlanosSaudeServiceImpl planosSaudeService;

	@Mock
	private PlanosSaudeRepository repository;

	@Mock
	private ModelMapper mapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBuscarTodos() {
		PlanosSaude plano1 = new PlanosSaude();
		plano1.setId(1L);
		plano1.setNome("Plano A");

		PlanosSaude plano2 = new PlanosSaude();
		plano2.setId(2L);
		plano2.setNome("Plano B");

		PlanosSaudeDTO dto1 = new PlanosSaudeDTO();
		dto1.setId(1L);
		dto1.setNome("Plano A");

		PlanosSaudeDTO dto2 = new PlanosSaudeDTO();
		dto2.setId(2L);
		dto2.setNome("Plano B");

		when(repository.findAll()).thenReturn(Arrays.asList(plano1, plano2));
		when(mapper.map(plano1, PlanosSaudeDTO.class)).thenReturn(dto1);
		when(mapper.map(plano2, PlanosSaudeDTO.class)).thenReturn(dto2);

		List<PlanosSaudeDTO> result = planosSaudeService.buscarTodos();

		assertEquals(2, result.size());
		assertEquals(dto1, result.get(0));
		assertEquals(dto2, result.get(1));

		verify(repository, times(1)).findAll();
		verify(mapper, times(1)).map(plano1, PlanosSaudeDTO.class);
		verify(mapper, times(1)).map(plano2, PlanosSaudeDTO.class);
	}
}