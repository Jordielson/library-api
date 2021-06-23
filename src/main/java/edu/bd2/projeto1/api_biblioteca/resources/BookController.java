package edu.bd2.projeto1.api_biblioteca.resources;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

import edu.bd2.projeto1.api_biblioteca.models.Livro;
import edu.bd2.projeto1.api_biblioteca.repository.LivroRepository;

@RestController
@RequestMapping(value="/libraryapi")
public class BookController {
	
	@Autowired
	LivroRepository livroRepository;
	
	@GetMapping("/book")
	public ResponseEntity<List<Livro>> getAll() {
		List<Livro> books = livroRepository.findAll();
		for (Livro book : books) {
			int id = book.getId();
			book.add(linkTo(methodOn(BookController.class).getBook(id)).withSelfRel());
		}
		return new ResponseEntity<List<Livro>>(books, HttpStatus.OK);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable(value="id") int id) {
		Optional<Livro> bookO = livroRepository.findById(id);
		if(!bookO.isPresent()) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		} else {
			bookO.get().add(linkTo(methodOn(BookController.class).getAll()).withRel("Book list"));
			return new ResponseEntity<Livro>(bookO.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/book")
	public ResponseEntity<Livro> saveBook(@RequestBody Livro book) {
		return new ResponseEntity<Livro>(livroRepository.save(book), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value="id") int id) {
		Optional<Livro> bookO = livroRepository.findById(id);
		if(!bookO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			livroRepository.delete(bookO.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PutMapping("/book")
	public ResponseEntity<Livro> updateBook(@RequestBody Livro book) {
		return new ResponseEntity<Livro>(livroRepository.save(book), HttpStatus.OK);
	}
}
