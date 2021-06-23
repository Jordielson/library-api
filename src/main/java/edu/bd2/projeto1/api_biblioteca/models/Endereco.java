package edu.bd2.projeto1.api_biblioteca.models;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	private String cidade;
	private String bairro;
	private String rua;
	
	public Endereco() {
	}

	public Endereco(String cidade, String bairro, String rua) {
		super();
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
	@Override
	public String toString() {
		return String.join(", ", cidade, bairro, rua);
	}
	
	
}
