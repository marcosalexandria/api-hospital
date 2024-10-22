package com.pdcase.hospital.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pdcase.hospital.entities.dto.FichaPacienteDTO;
import com.pdcase.hospital.exception.FichaJaExisteException;
import com.pdcase.hospital.exception.FichaNaoExisteException;
import com.pdcase.hospital.exception.ResultadoNaoEntontradoException;
import com.pdcase.hospital.services.FichaPacienteService;
import com.pdcase.hospital.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(value = "/fichas")
public class FichaPacienteResource {

	@Autowired
	FichaPacienteService service;

	@Operation(summary = "Busca uma ficha por nome e numero da carteira do paciente.", description = "Encontra todas as fichas associadas ao nome e numero da carteira do paciente fornecidos.", responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Retorna todas as fichas do paciente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma ficha encontrada.") })
	@GetMapping(value = "/buscar")
	public ResponseEntity<ApiResponse> buscarFichas(@RequestParam(required = true) String nomePaciente,
			@RequestParam(required = true) String numeroCarteiraPlano) {
		try {
			List<FichaPacienteDTO> list = service.buscarPorNomeECarteiraPlano(nomePaciente, numeroCarteiraPlano);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Resultados encontrados", list));
		} catch (ResultadoNaoEntontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@Operation(summary = "Salva uma nova ficha do paciente.", description = "Recebe os dados da ficha do paciente e salva no sistema.", 
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da ficha do paciente a ser salva.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FichaPacienteDTO.class))), responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Ficha salva com sucesso!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Ficha já existe.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro ao salvar a ficha.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))) })
	@PostMapping(value = "/salvar")
	public ResponseEntity<ApiResponse> salvarFicha(@RequestBody FichaPacienteDTO fichaPacienteDTO) {

		try {
			FichaPacienteDTO novaFicha = service.salvarFicha(fichaPacienteDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse("Ficha salva com sucesso!", novaFicha));

		} catch (FichaJaExisteException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Erro ao salvar a ficha!", null));
		}
	}

	@Operation(summary = "Atualiza uma ficha do paciente.", description = "Recebe os dados atualizados da ficha do paciente e aplica as alterações no sistema.", 
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados atualizados da ficha do paciente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FichaPacienteDTO.class))), responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ficha atualizada com sucesso!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Ficha não encontrada.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflito ao atualizar a ficha.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro ao atualizar a ficha.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))) })
	@PutMapping(value = "/atualizar")
	public ResponseEntity<ApiResponse> atualizarFicha(@RequestBody FichaPacienteDTO fichaPacienteDTO) {

		try {
			FichaPacienteDTO fichaAtualizada = service.alterarFicha(fichaPacienteDTO);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponse("Ficha atualizada com sucesso!", fichaAtualizada));

		} catch (FichaNaoExisteException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

		} catch (FichaJaExisteException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Erro ao atualizar a ficha!", null));
		}
	}

	@Operation(summary = "Exclui uma ficha do paciente pelo ID.", description = "Remove a ficha do paciente com base no ID fornecido.", responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Ficha excluída com sucesso, sem conteúdo na resposta."),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Ficha não encontrada."),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro ao tentar excluir a ficha.") })
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<ApiResponse> deletarFicha(@PathVariable Long id) {
		try {
			service.deletarFicha(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse("Ficha excluída com sucesso!", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(e.getMessage(), null));
		}
	}
}
