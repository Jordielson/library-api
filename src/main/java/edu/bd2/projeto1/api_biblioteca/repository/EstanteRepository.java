package edu.bd2.projeto1.api_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.bd2.projeto1.api_biblioteca.models.Estante;

public interface EstanteRepository extends JpaRepository<Estante, Integer>{
	
}
