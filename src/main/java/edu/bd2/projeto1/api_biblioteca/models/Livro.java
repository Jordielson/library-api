package edu.bd2.projeto1.api_biblioteca.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "livro")
public class Livro extends RepresentationModel<Livro> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String titulo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "editora")
	private Editora editora;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "autor_livro",
				joinColumns = @JoinColumn(name = "livro"),
				inverseJoinColumns = @JoinColumn(name = "autor"))
	private List<Autor> autores = new ArrayList<Autor>();
	
	@Column(name = "ano_publicacao")
	private int anoPublicacao;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "estante")
	private Estante estante;
	
	public Livro() {
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((autores == null) ? 0 : autores.hashCode());;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Livro)) {
			return false;
		}
		Livro l = (Livro) obj;
		return l.id == this.id;
	}
	
	@Override
	public String toString() {
		return "Livro [id="+id+", titulo="+titulo+", editora="+editora+", autor="+autores+", "+estante+"]";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public Autor getAutor(int i) {
		return autores.get(i);
	}
	public void addAutor(Autor a) {
		this.autores.add(a);
	}

	public Estante getEstante() {
		return estante;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}
}
