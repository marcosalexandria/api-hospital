package com.pdcase.hospital.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdcase.hospital.entities.dto.PlanosSaudeDTO;
import com.pdcase.hospital.services.PlanosSaudeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.pdcase.hospital.util.ApiResponse;

@RestController
@RequestMapping(value = "/planos")
public class PlanosSaudeResource {

	@Autowired
	PlanosSaudeService service;

	@Operation(summary = "Busca todos os planos de saúde.", description = "Retorna uma lista de todos os planos de saúde disponíveis no sistema.", responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de planos de saúde retornada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))) })
	@GetMapping
	public ResponseEntity<ApiResponse> buscarTodos() {
		List<PlanosSaudeDTO> list = service.buscarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Resultados encontrados", list));
	}

}
