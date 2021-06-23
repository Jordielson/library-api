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

import edu.bd2.projeto1.api_biblioteca.models.Usuario;
import edu.bd2.projeto1.api_biblioteca.repository.UsuarioRepository;

@RestController
@RequestMapping(value="/libraryapi")
public class UserController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/user")
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> users = usuarioRepository.findAll();
		for (Usuario u : users) {
			int id = u.getId();
			u.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
		}
		return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable(value="id") int id) {
		Optional<Usuario> userO = usuarioRepository.findById(id);
		if(!userO.isPresent()) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		} else {
			userO.get().add(linkTo(methodOn(UserController.class).getAll()).withRel("Users list"));
			return new ResponseEntity<Usuario>(userO.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/user")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user) {
		return new ResponseEntity<Usuario>(usuarioRepository.save(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") int id) {
		Optional<Usuario> userO = usuarioRepository.findById(id);
		if(!userO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			usuarioRepository.delete(userO.get());
			return new ResponseEntity<>(HttpStatus.OK);		
		}
	}
	
	@PutMapping("/user")
	public ResponseEntity<Usuario> updateUser(@RequestBody Usuario user) {
		return new ResponseEntity<Usuario>(usuarioRepository.save(user), HttpStatus.OK);
	}
}
