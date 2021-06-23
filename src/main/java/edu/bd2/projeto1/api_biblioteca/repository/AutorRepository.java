package edu.bd2.projeto1.api_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.bd2.projeto1.api_biblioteca.models.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
}
