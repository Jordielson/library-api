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

import edu.bd2.projeto1.api_biblioteca.models.Autor;
import edu.bd2.projeto1.api_biblioteca.repository.AutorRepository;

@RestController
@RequestMapping(value="/libraryapi")
public class AuthorController {
	
	@Autowired
	AutorRepository autorRepository;
	
	@GetMapping("/author")
	public ResponseEntity<List<Autor>> getAll() {
		List<Autor> authors = autorRepository.findAll();
		for (Autor author : authors) {
			int id = author.getId();
			author.add(linkTo(methodOn(AuthorController.class).getAuthor(id)).withSelfRel());
		}
		return new ResponseEntity<List<Autor>>(authors, HttpStatus.OK);
	}
	
	@GetMapping("/author/{id}")
	public ResponseEntity<Autor> getAuthor(@PathVariable(value="id") int id) {
		Optional<Autor> authorO = autorRepository.findById(id);
		if(!authorO.isPresent()) {
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		} else {
			authorO.get().add(linkTo(methodOn(AuthorController.class).getAll()).withRel("Author list"));
			return new ResponseEntity<Autor>(authorO.get(), HttpStatus.OK); 
		}
	}
	
	@PostMapping("/author")
	public ResponseEntity<Autor> saveAuthor(@RequestBody Autor author) {
		return new ResponseEntity<Autor>(autorRepository.save(author), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/author/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable(value="id") int id) {
		Optional<Autor> authorO = autorRepository.findById(id);
		if(!authorO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			autorRepository.delete(authorO.get());
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		
	}
	
	@PutMapping("/author")
	public ResponseEntity<Autor> updateAuthor(@RequestBody Autor author) {
		return new ResponseEntity<Autor>(autorRepository.save(author), HttpStatus.OK);
	}
}
