package edu.bd2.projeto1.api_biblioteca.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "cadastro_livro")
public class CadastroLivro extends RepresentationModel<CadastroLivro> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "livro_id")
	private Livro livro;
	
	@Column(name = "data_cad")
	private LocalDate data = LocalDate.now();
	
	public CadastroLivro() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CadastroLivro)) {
			return false;
		}
		CadastroLivro u = (CadastroLivro) obj;
		return this.id == u.id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Cadastro Livro [id=" + id + ", livro=" + livro.getTitulo() + ", Data=" + data;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
