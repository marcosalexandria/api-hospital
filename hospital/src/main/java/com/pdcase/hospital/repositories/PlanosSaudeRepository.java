package com.pdcase.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdcase.hospital.entities.PlanosSaude;

@Repository
public interface PlanosSaudeRepository extends JpaRepository<PlanosSaude, Long>{
	PlanosSaude findByIdAndNome(Long id, String nome);
}
