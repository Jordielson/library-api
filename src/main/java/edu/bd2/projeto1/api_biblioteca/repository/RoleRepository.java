package edu.bd2.projeto1.api_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.bd2.projeto1.api_biblioteca.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    
}
