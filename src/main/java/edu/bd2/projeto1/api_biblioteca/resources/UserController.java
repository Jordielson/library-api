package edu.bd2.projeto1.api_biblioteca.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bd2.projeto1.api_biblioteca.models.Usuario;
import edu.bd2.projeto1.api_biblioteca.models.login.JwtResposta;
import edu.bd2.projeto1.api_biblioteca.models.login.LoginDto;
import edu.bd2.projeto1.api_biblioteca.repository.RoleRepository;
import edu.bd2.projeto1.api_biblioteca.repository.UsuarioRepository;
import edu.bd2.projeto1.api_biblioteca.security.util.JwtUtils;

@RestController
@RequestMapping(value="/libraryapi")
public class UserController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/user")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user) {
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		user.addPapel(roleRepository.findById("ROLE_USER").get());
		return new ResponseEntity<Usuario>(usuarioRepository.save(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") String id) {
		Usuario user = usuarioRepository.findByCpf(id);
		if(user != null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			usuarioRepository.delete(user);
			return new ResponseEntity<>(HttpStatus.OK);		
		}
	}
	
	@PutMapping("/user")
	public ResponseEntity<Usuario> updateUser(@RequestBody Usuario user) {
		return new ResponseEntity<Usuario>(usuarioRepository.save(user), HttpStatus.OK);
	}

	@PostMapping("/login")
	public JwtResposta login(@Valid @RequestBody LoginDto loginRequest) {
		UsernamePasswordAuthenticationToken authenticationToken =
		 	new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getSenha());
		Authentication authentication = 
			authenticationManager.authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
			
		
		Usuario userDetails = (Usuario) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		JwtResposta resposta = new JwtResposta(jwt, 
				 userDetails.getCpf(), 
				 userDetails.getEmail(),
				 userDetails.getUsername(),
				 roles);
		
		return resposta;
	}
}
