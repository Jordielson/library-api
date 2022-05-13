package edu.bd2.projeto1.api_biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.bd2.projeto1.api_biblioteca.models.Usuario;
import edu.bd2.projeto1.api_biblioteca.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario userRecovered = usuarioRepository.findByCpf(username);
        if(userRecovered != null) {
            return userRecovered;
        }

        throw new UsernameNotFoundException("Usuario invalido!");
    }
}
