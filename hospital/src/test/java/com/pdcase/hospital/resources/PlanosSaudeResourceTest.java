package com.pdcase.hospital.resources;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pdcase.hospital.entities.dto.PlanosSaudeDTO;
import com.pdcase.hospital.services.PlanosSaudeService;

@WebMvcTest(PlanosSaudeResource.class)
public class PlanosSaudeResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlanosSaudeService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBuscarTodos() throws Exception {
		PlanosSaudeDTO dto1 = new PlanosSaudeDTO();
		dto1.setId(1L);
		dto1.setNome("Plano A");

		PlanosSaudeDTO dto2 = new PlanosSaudeDTO();
		dto2.setId(2L);
		dto2.setNome("Plano B");

		when(service.buscarTodos()).thenReturn(Arrays.asList(dto1, dto2));

		mockMvc.perform(get("/planos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.mensagem").value("Resultados encontrados"))
				.andExpect(jsonPath("$.dados[0].id").value(1L)).andExpect(jsonPath("$.dados[0].nome").value("Plano A"))
				.andExpect(jsonPath("$.dados[1].id").value(2L)).andExpect(jsonPath("$.dados[1].nome").value("Plano B"));

		verify(service, times(1)).buscarTodos();
	}
}