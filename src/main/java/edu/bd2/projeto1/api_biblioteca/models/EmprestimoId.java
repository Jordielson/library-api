package edu.bd2.projeto1.api_biblioteca.models;

import java.io.Serializable;

public class EmprestimoId implements Serializable {
    private static final long serialVersionUID = 1L;

	private int cadastroId;
    
	private int usuarioId;
    
    public EmprestimoId() {
	}

	public int getCadastroId() {
		return cadastroId;
	}

	public void setCadastroId(int cadastroId) {
		this.cadastroId = cadastroId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
}
