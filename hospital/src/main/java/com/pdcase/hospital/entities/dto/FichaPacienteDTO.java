package com.pdcase.hospital.entities.dto;

import com.pdcase.hospital.entities.Especialidades;
import com.pdcase.hospital.entities.PlanosSaude;

public class FichaPacienteDTO {
	private Long id;
	private String nomePaciente;
	private String numeroCarteiraPlano;
	private PlanosSaude planosSaude;
	private Especialidades especialidades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getNumeroCarteiraPlano() {
		return numeroCarteiraPlano;
	}
	public void setNumeroCarteiraPlano(String numeroCarteiraPlano) {
		this.numeroCarteiraPlano = numeroCarteiraPlano;
	}
	public PlanosSaude getPlanosSaude() {
		return planosSaude;
	}
	public void setPlanosSaude(PlanosSaude planosSaude) {
		this.planosSaude = planosSaude;
	}
	public Especialidades getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(Especialidades especialidades) {
		this.especialidades = especialidades;
	}

	
}
