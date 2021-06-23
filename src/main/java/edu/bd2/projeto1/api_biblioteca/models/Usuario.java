package edu.bd2.projeto1.api_biblioteca.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;


@Entity
@Table(name = "usuario")
public class Usuario extends RepresentationModel<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cpf;
	private Endereco endereco;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "email", joinColumns = @JoinColumn(name = "usuario"))
	@Column(name = "email")
	private List<String> email = new ArrayList<String>();
	
	public Usuario() {
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Usuario)) {
			return false;
		}
		Usuario u = (Usuario) obj;
		return this.id == u.id;
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
		return "Usuario [id="+id+"; nome="+nome+"; endereco="+endereco+"; email="+String.join(", ", email)+"]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
}
