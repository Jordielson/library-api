package edu.bd2.projeto1.api_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.bd2.projeto1.api_biblioteca.models.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer>{

}
