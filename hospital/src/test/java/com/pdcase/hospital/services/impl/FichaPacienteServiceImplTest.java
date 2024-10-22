package com.pdcase.hospital.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.pdcase.hospital.entities.Especialidades;
import com.pdcase.hospital.entities.FichaPaciente;
import com.pdcase.hospital.entities.PlanosSaude;
import com.pdcase.hospital.entities.dto.FichaPacienteDTO;
import com.pdcase.hospital.exception.FichaJaExisteException;
import com.pdcase.hospital.exception.ResultadoNaoEntontradoException;
import com.pdcase.hospital.repositories.FichaPacienteRepository;

public class FichaPacienteServiceImplTest {

    @Mock
    private FichaPacienteRepository fichaPacienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FichaPacienteServiceImpl fichaPacienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarFicha_FichaJaExiste() {
        FichaPacienteDTO dto = new FichaPacienteDTO();
        dto.setNomePaciente("João");
        dto.setNumeroCarteiraPlano("12345");
        dto.setPlanosSaude(new PlanosSaude(1L, "Plano A"));
        dto.setEspecialidades(new Especialidades(1L, "Cardiologia"));

        FichaPaciente fichaExistente = new FichaPaciente();
        fichaExistente.setNomePaciente("João");
        fichaExistente.setNumeroCarteiraPlano("12345");

        when(fichaPacienteRepository.buscarFichaExiste(
                dto.getNomePaciente(),
                dto.getNumeroCarteiraPlano(),
                dto.getPlanosSaude().getId(),
                dto.getEspecialidades().getId()
        )).thenReturn(fichaExistente);

        assertThrows(FichaJaExisteException.class, () -> {
            fichaPacienteService.salvarFicha(dto);
        });

        verify(fichaPacienteRepository, times(1)).buscarFichaExiste(
                dto.getNomePaciente(),
                dto.getNumeroCarteiraPlano(),
                dto.getPlanosSaude().getId(),
                dto.getEspecialidades().getId()
        );
    }

    @Test
    public void testSalvarFicha_Success() {
        FichaPacienteDTO dto = new FichaPacienteDTO();
        dto.setNomePaciente("Maria");
        dto.setNumeroCarteiraPlano("67890");
        dto.setPlanosSaude(new PlanosSaude(2L, "Plano B"));
        dto.setEspecialidades(new Especialidades(2L, "Ortopedia"));

        FichaPaciente fichaNova = new FichaPaciente();
        fichaNova.setNomePaciente("Maria");
        fichaNova.setNumeroCarteiraPlano("67890");

        when(fichaPacienteRepository.buscarFichaExiste(
                dto.getNomePaciente(),
                dto.getNumeroCarteiraPlano(),
                dto.getPlanosSaude().getId(),
                dto.getEspecialidades().getId()
        )).thenReturn(null);

        when(modelMapper.map(dto, FichaPaciente.class)).thenReturn(fichaNova);
        when(fichaPacienteRepository.save(fichaNova)).thenReturn(fichaNova);
        when(modelMapper.map(fichaNova, FichaPacienteDTO.class)).thenReturn(dto);

        FichaPacienteDTO resultado = fichaPacienteService.salvarFicha(dto);

        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNomePaciente());
        assertEquals("67890", resultado.getNumeroCarteiraPlano());

        verify(fichaPacienteRepository, times(1)).buscarFichaExiste(
                dto.getNomePaciente(),
                dto.getNumeroCarteiraPlano(),
                dto.getPlanosSaude().getId(),
                dto.getEspecialidades().getId()
        );
        verify(fichaPacienteRepository, times(1)).save(fichaNova);
    }

    @Test
    public void testAlterarFicha_FichaNaoEncontrada() {
        FichaPacienteDTO dto = new FichaPacienteDTO();
        dto.setId(1L);

        when(fichaPacienteRepository.findById(dto.getId())).thenReturn(Optional.empty());

    }

    @Test
    public void testAlterarFicha_Success() {
        FichaPacienteDTO dto = new FichaPacienteDTO();
        dto.setId(1L);
        dto.setNomePaciente("Carlos");
        dto.setNumeroCarteiraPlano("54321");
        dto.setPlanosSaude(new PlanosSaude(1L, "Plano A"));
        dto.setEspecialidades(new Especialidades(1L, "Neurologia"));

        FichaPaciente fichaBanco = new FichaPaciente();
        fichaBanco.setId(1L);
        fichaBanco.setNomePaciente("Carlos");
        fichaBanco.setNumeroCarteiraPlano("54321");

        when(fichaPacienteRepository.findById(dto.getId())).thenReturn(Optional.of(fichaBanco));
        when(modelMapper.map(dto, FichaPaciente.class)).thenReturn(fichaBanco);
        when(fichaPacienteRepository.save(fichaBanco)).thenReturn(fichaBanco);
        when(modelMapper.map(fichaBanco, FichaPacienteDTO.class)).thenReturn(dto);

        FichaPacienteDTO resultado = fichaPacienteService.alterarFicha(dto);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNomePaciente());
        assertEquals("54321", resultado.getNumeroCarteiraPlano());

        verify(fichaPacienteRepository, times(1)).findById(dto.getId());
        verify(fichaPacienteRepository, times(1)).save(fichaBanco);
    }

    @Test
    public void testBuscarPorNomeECarteiraPlano_Encontrado() {
        FichaPaciente ficha1 = new FichaPaciente();
        ficha1.setNomePaciente("Ana");
        ficha1.setNumeroCarteiraPlano("12345");

        FichaPaciente ficha2 = new FichaPaciente();
        ficha2.setNomePaciente("Pedro");
        ficha2.setNumeroCarteiraPlano("67890");

        List<FichaPaciente> listaFichas = Arrays.asList(ficha1, ficha2);

        when(fichaPacienteRepository.findByNomePacienteAndNumeroCarteiraPlano("Ana", "12345")).thenReturn(listaFichas);

        List<FichaPacienteDTO> resultado = fichaPacienteService.buscarPorNomeECarteiraPlano("Ana", "12345");
        FichaPacienteDTO dto = new FichaPacienteDTO();
        resultado.add(modelMapper.map(ficha1, dto.getClass()));
        resultado.add(modelMapper.map(ficha2, dto.getClass()));

        assertNotNull(resultado);
        assertEquals(4, resultado.size());

        verify(fichaPacienteRepository, times(1)).findByNomePacienteAndNumeroCarteiraPlano("Ana", "12345");
    }

    @Test
    public void testBuscarPorNomeECarteiraPlano_NaoEncontrado() {
        when(fichaPacienteRepository.findByNomePacienteAndNumeroCarteiraPlano("Ana", "12345"))
                .thenReturn(Arrays.asList());

        assertThrows(ResultadoNaoEntontradoException.class, () -> {
            fichaPacienteService.buscarPorNomeECarteiraPlano("Ana", "12345");
        });

        verify(fichaPacienteRepository, times(1)).findByNomePacienteAndNumeroCarteiraPlano("Ana", "12345");
    }

    @Test
    public void testDeletarFicha_FichaNaoEncontrada() {
        Long id = 1L;

        when(fichaPacienteRepository.findById(id)).thenReturn(Optional.empty());

    }

    @Test
    public void testDeletarFicha_Success() throws Exception {
        Long id = 1L;
        FichaPaciente ficha = new FichaPaciente();
        ficha.setId(id);

        when(fichaPacienteRepository.findById(id)).thenReturn(Optional.of(ficha));

        fichaPacienteService.deletarFicha(id);

        verify(fichaPacienteRepository, times(1)).findById(id);
        verify(fichaPacienteRepository, times(1)).delete(ficha);
    }
}