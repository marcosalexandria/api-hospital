package com.pdcase.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pdcase.hospital.entities.FichaPaciente;

@Repository
public interface FichaPacienteRepository extends JpaRepository<FichaPaciente, Long>{
	
	List<FichaPaciente> findByNomePacienteAndNumeroCarteiraPlano(String nomePaciente, String numeroCarteiraPlano);
	
	
	   @Query("SELECT f FROM FichaPaciente f WHERE f.nomePaciente = :nomePaciente AND f.numeroCarteiraPlano = :numeroCarteiraPlano AND f.planosSaude.id = :planosSaude AND f.especialidades.id = :especialidades")
	    FichaPaciente buscarFichaExiste(
	    		@Param("nomePaciente") String nomePaciente,
	            @Param("numeroCarteiraPlano") String numeroCarteiraPlano,
	            @Param("planosSaude") Long planosSaude,
	            @Param("especialidades") Long especialidades);
	   
	   
}
