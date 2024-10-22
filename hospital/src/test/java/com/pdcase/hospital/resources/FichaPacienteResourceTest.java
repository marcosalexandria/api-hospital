package com.pdcase.hospital.resources;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdcase.hospital.entities.dto.FichaPacienteDTO;
import com.pdcase.hospital.exception.FichaJaExisteException;
import com.pdcase.hospital.exception.FichaNaoExisteException;
import com.pdcase.hospital.services.FichaPacienteService;

@WebMvcTest(FichaPacienteResource.class)
public class FichaPacienteResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FichaPacienteService service;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarFichas_Encontrado() throws Exception {
        FichaPacienteDTO fichaDTO = new FichaPacienteDTO();
        fichaDTO.setId(1L);
        fichaDTO.setNomePaciente("João da Silva");
        fichaDTO.setNumeroCarteiraPlano("123456789");

        List<FichaPacienteDTO> listaFichas = Arrays.asList(fichaDTO);

        when(service.buscarPorNomeECarteiraPlano("João da Silva", "123456789"))
                .thenReturn(listaFichas);

        mockMvc.perform(get("/fichas/buscar")
                .param("nomePaciente", "João da Silva")
                .param("numeroCarteiraPlano", "123456789")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).buscarPorNomeECarteiraPlano("João da Silva", "123456789");
    }

    @Test
    public void testSalvarFicha_Sucesso() throws Exception {
        FichaPacienteDTO fichaDTO = new FichaPacienteDTO();
        fichaDTO.setId(1L);
        fichaDTO.setNomePaciente("João da Silva");
        fichaDTO.setNumeroCarteiraPlano("123456789");

        when(service.salvarFicha(fichaDTO)).thenReturn(fichaDTO);

        mockMvc.perform(post("/fichas/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fichaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSalvarFicha_Conflito() throws Exception {
        FichaPacienteDTO fichaDTO = new FichaPacienteDTO();
        fichaDTO.setNomePaciente("João da Silva");
        fichaDTO.setNumeroCarteiraPlano("123456789");

        when(service.salvarFicha(fichaDTO)).thenThrow(new FichaJaExisteException("Ficha já existente"));

        mockMvc.perform(post("/fichas/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fichaDTO)));

    }

    @Test
    public void testAtualizarFicha_Sucesso() throws Exception {
        FichaPacienteDTO fichaDTO = new FichaPacienteDTO();
        fichaDTO.setId(1L);
        fichaDTO.setNomePaciente("João da Silva");
        fichaDTO.setNumeroCarteiraPlano("123456789");

        when(service.alterarFicha(fichaDTO)).thenReturn(fichaDTO);

        mockMvc.perform(put("/fichas/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fichaDTO)))
                .andExpect(status().isOk());

    }

    @Test
    public void testAtualizarFicha_NaoEncontrada() throws Exception {
        FichaPacienteDTO fichaDTO = new FichaPacienteDTO();
        fichaDTO.setId(1L);

        when(service.alterarFicha(fichaDTO)).thenThrow(new FichaNaoExisteException("Ficha não encontrada"));

        mockMvc.perform(put("/fichas/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fichaDTO)));
    }

    @Test
    public void testDeletarFicha_Sucesso() throws Exception {
        doNothing().when(service).deletarFicha(1L);

        mockMvc.perform(delete("/fichas/deletar/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deletarFicha(1L);
    }

    @Test
    public void testDeletarFicha_Erro() throws Exception {
        doThrow(new RuntimeException("Erro ao deletar")).when(service).deletarFicha(1L);

        mockMvc.perform(delete("/fichas/deletar/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deletarFicha(1L);
    }
}