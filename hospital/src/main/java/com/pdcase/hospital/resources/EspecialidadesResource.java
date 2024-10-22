package com.pdcase.hospital.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdcase.hospital.entities.dto.EspecialidadesDTO;
import com.pdcase.hospital.services.EspecialidadesService;
import com.pdcase.hospital.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadesResource {

	@Autowired
	EspecialidadesService service;

	@Operation(summary = "Busca todas as especialidades.", description = "Retorna uma lista de todas as especialidades disponíveis no sistema.", responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de especialidades retornada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))) })
	@GetMapping
	public ResponseEntity<ApiResponse> buscarTodos() {
		List<EspecialidadesDTO> list = service.buscarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Resultados encontrados", list));
	}
}
