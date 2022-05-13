package edu.bd2.projeto1.api_biblioteca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bd2.projeto1.api_biblioteca.models.Usuario;
import edu.bd2.projeto1.api_biblioteca.repository.UsuarioRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByCpf(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario nao encontrado!");
        }
        return new User(user.getNome(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
    
}
