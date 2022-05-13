package edu.bd2.projeto1.api_biblioteca.models.login;

import javax.validation.constraints.NotBlank;

public class LoginDto {

	@NotBlank
	public String login;
	
	@NotBlank
	public String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}

