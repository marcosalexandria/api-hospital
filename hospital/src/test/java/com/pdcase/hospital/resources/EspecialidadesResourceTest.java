package com.pdcase.hospital.resources;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pdcase.hospital.entities.dto.EspecialidadesDTO;
import com.pdcase.hospital.services.EspecialidadesService;

@WebMvcTest(EspecialidadesResource.class)
public class EspecialidadesResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EspecialidadesService especialidadesService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBuscarTodos() throws Exception {
		EspecialidadesDTO especialidade1 = new EspecialidadesDTO();
		especialidade1.setId(1L);
		especialidade1.setNome("Cardiologia");

		EspecialidadesDTO especialidade2 = new EspecialidadesDTO();
		especialidade2.setId(2L);
		especialidade2.setNome("Ortopedia");

		List<EspecialidadesDTO> listaEspecialidades = Arrays.asList(especialidade1, especialidade2);

		when(especialidadesService.buscarTodos()).thenReturn(listaEspecialidades);

		mockMvc.perform(get("/especialidades").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.mensagem").value("Resultados encontrados"))
				.andExpect(jsonPath("$.dados.length()").value(2)).andExpect(jsonPath("$.dados[0].id").value(1L))
				.andExpect(jsonPath("$.dados[0].nome").value("Cardiologia"))
				.andExpect(jsonPath("$.dados[1].id").value(2L))
				.andExpect(jsonPath("$.dados[1].nome").value("Ortopedia"));

		verify(especialidadesService, times(1)).buscarTodos();
	}
}