package edu.bd2.projeto1.api_biblioteca.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

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

import edu.bd2.projeto1.api_biblioteca.models.CadastroLivro;
import edu.bd2.projeto1.api_biblioteca.repository.CadastroLivroRepository;

@RestController
@RequestMapping(value="/libraryapi")
public class RegisterController {
	
	@Autowired
	CadastroLivroRepository cadastroLivroRepository;
	
	@GetMapping("/registerbook")
	public ResponseEntity<List<CadastroLivro>> getAll() {
		List<CadastroLivro> registers = cadastroLivroRepository.findAll();
		for (CadastroLivro u : registers) {
			int id = u.getId();
			u.add(linkTo(methodOn(RegisterController.class).getRegister(id)).withSelfRel());
		}
		return new ResponseEntity<List<CadastroLivro>>(registers, HttpStatus.OK);
	}
	
	@GetMapping("/registerbook/{id}")
	public ResponseEntity<CadastroLivro> getRegister(@PathVariable(value="id") int id) {
		Optional<CadastroLivro> registerO = cadastroLivroRepository.findById(id);
		if(!registerO.isPresent()) {
			return new ResponseEntity<CadastroLivro>(HttpStatus.NOT_FOUND);
		} else {
			registerO.get().add(linkTo(methodOn(RegisterController.class).getAll()).withRel("Register list"));
			return new ResponseEntity<CadastroLivro>(registerO.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/registerbook")
	public ResponseEntity<CadastroLivro> saveRegister(@RequestBody CadastroLivro register) {
		return new ResponseEntity<CadastroLivro>(cadastroLivroRepository.save(register), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/registerbook/{id}")
	public ResponseEntity<?> deleteRegister(@PathVariable(value="id") int id) {
		Optional<CadastroLivro> registerO = cadastroLivroRepository.findById(id);
		if(!registerO.isPresent()) {
			return new ResponseEntity<CadastroLivro>(HttpStatus.NOT_FOUND);
		} else {
			cadastroLivroRepository.delete(registerO.get());
			return new ResponseEntity<CadastroLivro>(HttpStatus.OK);
		}
	}
	
	@PutMapping("/registerbook")
	public ResponseEntity<CadastroLivro> updateRegister(@RequestBody CadastroLivro register) {
		return new ResponseEntity<CadastroLivro>(cadastroLivroRepository.save(register), HttpStatus.OK);
	}
}
