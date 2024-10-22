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

import com.pdcase.hospital.entities.Especialidades;
import com.pdcase.hospital.entities.dto.EspecialidadesDTO;
import com.pdcase.hospital.repositories.EspecialidadesRepository;

public class EspecialidadesServicesImplTest {

	@InjectMocks
	private EspecialidadesServicesImpl especialidadesService;

	@Mock
	private EspecialidadesRepository repository;

	@Mock
	private ModelMapper mapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBuscarTodos() {
		Especialidades especialidade1 = new Especialidades();
		especialidade1.setId(1L);
		especialidade1.setNome("Cardiologia");

		Especialidades especialidade2 = new Especialidades();
		especialidade2.setId(2L);
		especialidade2.setNome("Ortopedia");

		EspecialidadesDTO dto1 = new EspecialidadesDTO();
		dto1.setId(1L);
		dto1.setNome("Cardiologia");

		EspecialidadesDTO dto2 = new EspecialidadesDTO();
		dto2.setId(2L);
		dto2.setNome("Ortopedia");

		when(repository.findAll()).thenReturn(Arrays.asList(especialidade1, especialidade2));
		when(mapper.map(especialidade1, EspecialidadesDTO.class)).thenReturn(dto1);
		when(mapper.map(especialidade2, EspecialidadesDTO.class)).thenReturn(dto2);

		List<EspecialidadesDTO> result = especialidadesService.buscarTodos();

		assertEquals(2, result.size());
		assertEquals(dto1, result.get(0));
		assertEquals(dto2, result.get(1));

		verify(repository, times(1)).findAll();
		verify(mapper, times(1)).map(especialidade1, EspecialidadesDTO.class);
		verify(mapper, times(1)).map(especialidade2, EspecialidadesDTO.class);
	}
}