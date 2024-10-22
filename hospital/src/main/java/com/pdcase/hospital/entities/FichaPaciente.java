package com.pdcase.hospital.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ficha_paciente")
public class FichaPaciente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nome_paciente")
    private String nomePaciente;
	
	@Column(name = "numero_carteira_plano")
    private String numeroCarteiraPlano;

    @ManyToOne
    @JoinColumn(name = "id_plano_de_saude")
    private PlanosSaude planosSaude;

    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidades especialidades;

    public FichaPaciente() {
	}

	public FichaPaciente(Long id, String nomePaciente, String numeroCarteiraPlano, PlanosSaude planosSaude,
			Especialidades especialidades) {
		this.id = id;
		this.nomePaciente = nomePaciente;
		this.numeroCarteiraPlano = numeroCarteiraPlano;
		this.planosSaude = planosSaude;
		this.especialidades = especialidades;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FichaPaciente other = (FichaPaciente) obj;
		return Objects.equals(id, other.id);
	}
    
}