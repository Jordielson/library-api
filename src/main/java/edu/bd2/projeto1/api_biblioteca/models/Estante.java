package edu.bd2.projeto1.api_biblioteca.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "estante")
public class Estante extends RepresentationModel<Estante> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int corredor;
	
	public Estante() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCorredor() {
		return corredor;
	}

	public void setCorredor(int corredor) {
		this.corredor = corredor;
	}
	
	@Override
	public String toString() {
		return "Estante[id=" + id + ", corredor="+corredor+"]";
	}
}