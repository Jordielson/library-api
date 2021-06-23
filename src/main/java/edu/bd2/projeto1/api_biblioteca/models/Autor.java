package edu.bd2.projeto1.api_biblioteca.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "autor")
public class Autor extends RepresentationModel<Autor> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "primeiro_nome")
	private String primeroNome;
	@Column(name = "sobrenome")
	private String sobreNome;
	
	public Autor() {
	}
	public Autor(String primeroNome, String sobreNome) {
		super();
		this.primeroNome = primeroNome;
		this.sobreNome = sobreNome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrimeroNome() {
		return primeroNome;
	}

	public void setPrimeroNome(String primeroNome) {
		this.primeroNome = primeroNome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	
	@Override
	public String toString() {
		return sobreNome + " " + primeroNome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 7;
		int result = id;
		result = prime * result + ((primeroNome == null) ? 0 : primeroNome.hashCode());
		result = prime * result + ((sobreNome == null) ? 0 : sobreNome.hashCode());
		return result;
	}
}
