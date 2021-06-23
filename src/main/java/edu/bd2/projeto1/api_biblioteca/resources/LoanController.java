package edu.bd2.projeto1.api_biblioteca.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bd2.projeto1.api_biblioteca.models.Emprestimo;
import edu.bd2.projeto1.api_biblioteca.repository.EmprestimoRepository;

@RestController
@RequestMapping(value="/libraryapi")
public class LoanController {
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@GetMapping("/loan")
	public ResponseEntity<List<Emprestimo>> getAll() {
		List<Emprestimo> loans = emprestimoRepository.findAll();
		for (Emprestimo l : loans) {
			int id = l.getCadastroId().getId();
			l.add(linkTo(methodOn(LoanController.class).getLoan(id)).withSelfRel());
		}
		return new ResponseEntity<List<Emprestimo>>(loans, HttpStatus.OK);
	}
	
	@GetMapping("/loan/{id}")//id do cadastro do livro
	public ResponseEntity<Emprestimo> getLoan(@PathVariable(value="id") int id) {
		Emprestimo loan = emprestimoRepository.findById(id);
		if(loan == null) {
			return new ResponseEntity<Emprestimo>(HttpStatus.NOT_FOUND);
		} else {
			loan.add(linkTo(methodOn(LoanController.class).getAll()).withRel("Loan list"));
			return new ResponseEntity<Emprestimo>(loan, HttpStatus.OK);
		}
	}
	
	@PostMapping("/loan")
	public ResponseEntity<Emprestimo> saveLoan(@RequestBody Emprestimo loan) {
		return new ResponseEntity<Emprestimo>(emprestimoRepository.save(loan), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/loan/{id}")
	public ResponseEntity<?> deleteLoan(@PathVariable(value="id") int id) {
		Emprestimo loan = emprestimoRepository.findById(id);
		if(loan == null) {
			return new ResponseEntity<Emprestimo>(HttpStatus.NOT_FOUND);
		} else {
			emprestimoRepository.delete(loan);
			return new ResponseEntity<Emprestimo>(HttpStatus.OK);
		}
	}
	
	@PutMapping("/loan")
	public ResponseEntity<Emprestimo> updateLoan(@RequestBody Emprestimo loan) {
		return new ResponseEntity<Emprestimo>(emprestimoRepository.save(loan), HttpStatus.OK);
	}
}
