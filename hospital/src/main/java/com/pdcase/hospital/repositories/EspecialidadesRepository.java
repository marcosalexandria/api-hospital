package com.pdcase.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdcase.hospital.entities.Especialidades;

@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Long>{
	
	Especialidades findByIdAndNome(Long id, String nome);
}
