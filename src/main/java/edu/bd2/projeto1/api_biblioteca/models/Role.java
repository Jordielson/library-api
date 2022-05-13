package edu.bd2.projeto1.api_biblioteca.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    private String papel;

    @ManyToMany(mappedBy = "papeis")
    private List<Usuario> usuarios;

    public Role() {}

    @Override
    public String getAuthority() {
        return papel;
    }

    public String getPapel() {
        return papel;
    }
    public void setPapel(String papel) {
        this.papel = papel;
    }
    
    @Override
    public String toString() {
        return papel;
    }
}
