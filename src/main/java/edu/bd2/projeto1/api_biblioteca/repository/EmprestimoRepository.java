package edu.bd2.projeto1.api_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.bd2.projeto1.api_biblioteca.models.CadastroLivro;
import edu.bd2.projeto1.api_biblioteca.models.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, CadastroLivro>{

	@Query(value = "select * from emprestimo e join cadastro_livro c on c.id = e.cadastro_id where c.id = ?1", nativeQuery = true)
	Emprestimo findById(int id);
}
