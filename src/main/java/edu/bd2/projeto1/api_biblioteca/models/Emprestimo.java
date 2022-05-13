package edu.bd2.projeto1.api_biblioteca.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

@Entity
@IdClass(EmprestimoId.class)
@Table(name = "emprestimo")
public class Emprestimo extends RepresentationModel<Emprestimo> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "cadastro_id")
	private CadastroLivro cadastroId;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioId;

	private LocalDate data = LocalDate.now();
	
	public Emprestimo() {
	}
	public Emprestimo(CadastroLivro livro, Usuario usuario) {
		super();
		this.cadastroId = livro;
		this.usuarioId = usuario;
	}
	
	public CadastroLivro getCadastroId() {
		return cadastroId;
	}
	public void setCadastroId(CadastroLivro cadastroId) {
		this.cadastroId = cadastroId;
	}
	public Usuario getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Emprestimo)) {
			return false;
		}
		Emprestimo e = (Emprestimo) obj;
		return this.cadastroId == e.cadastroId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadastroId == null) ? 0 : cadastroId.hashCode());
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Usuario(cof="+ usuarioId.getCpf()+", nome="+ usuarioId.getNome() + "), Livro(id="+ cadastroId.getId()+", nome=" + cadastroId.getLivro().getTitulo() + "), Data: " + data;
	}
}
