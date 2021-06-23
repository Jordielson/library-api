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

import edu.bd2.projeto1.api_biblioteca.models.Estante;
import edu.bd2.projeto1.api_biblioteca.repository.EstanteRepository;

@RestController
@RequestMapping(value="/libraryapi")
public class BookcaseController {

	@Autowired
	EstanteRepository estanteRepository;
	
	@GetMapping("/bookcase")
	public ResponseEntity<List<Estante>> getAll() {
		List<Estante> bookcases = estanteRepository.findAll();
		for (Estante bookcase : bookcases) {
			int id = bookcase.getId();
			bookcase.add(linkTo(methodOn(BookcaseController.class).getBookcase(id)).withSelfRel());
		}
		return new ResponseEntity<List<Estante>>(bookcases, HttpStatus.OK);
	}
	
	@GetMapping("/bookcase/{id}")
	public ResponseEntity<Estante> getBookcase(@PathVariable(value="id") int id) {
		Optional<Estante> bookcaseO = estanteRepository.findById(id);
		if(!bookcaseO.isPresent()) {
			return new ResponseEntity<Estante>(HttpStatus.NOT_FOUND);
		} else {
			bookcaseO.get().add(linkTo(methodOn(BookcaseController.class).getAll()).withRel("Bookcase list"));
			return new ResponseEntity<Estante>(bookcaseO.get(), HttpStatus.OK); 
		}
	}
	
	@PostMapping("/bookcase")
	public ResponseEntity<Estante> saveBookcase(@RequestBody Estante bookcase) {
		return new ResponseEntity<Estante>(estanteRepository.save(bookcase), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/bookcase/{id}")
	public ResponseEntity<?> deleteBookcase(@PathVariable(value="id") int id) {
		Optional<Estante> bookcaseO = estanteRepository.findById(id);
		if(!bookcaseO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			estanteRepository.delete(bookcaseO.get());
			return new ResponseEntity<>(HttpStatus.OK); 
		}
	}
	
	@PutMapping("/bookcase")
	public ResponseEntity<Estante> updateBookcase(@RequestBody Estante bookcase) {
		return new ResponseEntity<Estante>(estanteRepository.save(bookcase), HttpStatus.OK);
	}
}
