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

import edu.bd2.projeto1.api_biblioteca.models.Editora;
import edu.bd2.projeto1.api_biblioteca.repository.EditoraRepository;

@RestController
@RequestMapping(value="libraryapi")
public class PublisherController {

	@Autowired
	private EditoraRepository editoraRepository;
	
	@GetMapping("/publisher")
	public ResponseEntity<List<Editora>> getAll() {
		List<Editora> publisers = editoraRepository.findAll();
		for (Editora publ : publisers) {
			int id = publ.getId();
			publ.add(linkTo(methodOn(PublisherController.class).getPublisher(id)).withSelfRel());
		}
		return new ResponseEntity<List<Editora>>(publisers, HttpStatus.OK);
	}
	
	@GetMapping("/publisher/{id}")
	public ResponseEntity<Editora> getPublisher(@PathVariable(value="id") int id) {
		Optional<Editora> publisherO = editoraRepository.findById(id);
		if(!publisherO.isPresent()) {
			return new ResponseEntity<Editora>(HttpStatus.NOT_FOUND);
		} else {
			publisherO.get().add(linkTo(methodOn(PublisherController.class).getAll()).withRel("Publisher list"));
			return new ResponseEntity<Editora>(publisherO.get(), HttpStatus.OK); 
		}
	}
	
	@PostMapping("/publisher")
	public ResponseEntity<Editora> savePublisher(@RequestBody Editora publisher) {
		return new ResponseEntity<Editora>(editoraRepository.save(publisher), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/publisher/{id}")
	public ResponseEntity<?> deletePublisher(@PathVariable int id) {
		Optional<Editora> publisherO = editoraRepository.findById(id);
		if(!publisherO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			editoraRepository.delete(publisherO.get());
			return new ResponseEntity<>(HttpStatus.OK); 
		}
	}
	
	@PutMapping("/publisher")
	public ResponseEntity<Editora> updatePublisher(@RequestBody Editora publisher) {
		return new ResponseEntity<Editora>(editoraRepository.save(publisher), HttpStatus.OK);
	}
}
