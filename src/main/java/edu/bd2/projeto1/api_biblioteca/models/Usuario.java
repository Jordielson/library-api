package edu.bd2.projeto1.api_biblioteca.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "usuario")
public class Usuario extends RepresentationModel<Usuario> implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nome;
	@Id
	@Column(nullable = false, length = 20)
	private String cpf;
	@Column(nullable = false)
	@Size(min = 3, message = "A senha deve possuir mais 3 caracteres")
	private String senha;
	private Endereco endereco;
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "email", joinColumns = @JoinColumn(name = "usuario"))
	@Column(name = "email")
	private List<String> email = new ArrayList<String>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "role_user",
		joinColumns = @JoinColumn(
			name = "user_id",
			referencedColumnName = "cpf"
		),
		inverseJoinColumns = @JoinColumn(
			name = "role_id",
			referencedColumnName = "papel"
		) 
	)
	private List<Role> papeis = new ArrayList<Role>();
	
	public Usuario() {
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Usuario)) {
			return false;
		}
		Usuario u = (Usuario) obj;
		return this.cpf == u.cpf;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Usuario [cpf="+cpf+"; nome="+nome+"; endereco="+endereco+"; email="+String.join(", ", email)+"]";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	public void addEmail(String email) {
		this.email.add(email);
	}
	public void removerEmail(int i) {
		this.email.remove(i);
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.papeis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public Usuario(String nome, String cpf, String senha, Endereco endereco, List<String> email, List<Role> papeis) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.endereco = endereco;
		this.email = email;
		this.papeis = papeis;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Role> getPapeis() {
		return this.papeis;
	}

	public void setPapeis(List<Role> papeis) {
		this.papeis = papeis;
	}

	public void addPapel(Role papel) {
		if(!papeis.contains(papel)) {
			papeis.add(papel);
		}
	}

	public Usuario nome(String nome) {
		setNome(nome);
		return this;
	}

	public Usuario cpf(String cpf) {
		setCpf(cpf);
		return this;
	}

	public Usuario senha(String senha) {
		setSenha(senha);
		return this;
	}

	public Usuario endereco(Endereco endereco) {
		setEndereco(endereco);
		return this;
	}

	public Usuario email(List<String> email) {
		setEmail(email);
		return this;
	}

	public Usuario papeis(List<Role> papeis) {
		setPapeis(papeis);
		return this;
	}

}
