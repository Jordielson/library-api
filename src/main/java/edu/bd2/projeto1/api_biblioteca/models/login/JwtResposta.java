package edu.bd2.projeto1.api_biblioteca.models.login;

import java.util.List;
import java.util.Objects;

public class JwtResposta {
    private String token;
	private String type = "Bearer";
	private String id;
	private String login;
	private List<String> email;
	private String nome;
	private List<String> roles;


    public JwtResposta() {
    }

    public JwtResposta(String token, String login, List<String> email, String nome, List<String> roles) {
        this.token = token;
        this.login = login;
        this.email = email;
        this.nome = nome;
        this.roles = roles;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getEmail() {
        return this.email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public JwtResposta token(String token) {
        setToken(token);
        return this;
    }

    public JwtResposta type(String type) {
        setType(type);
        return this;
    }

    public JwtResposta id(String id) {
        setId(id);
        return this;
    }

    public JwtResposta login(String login) {
        setLogin(login);
        return this;
    }

    public JwtResposta email(List<String> email) {
        setEmail(email);
        return this;
    }

    public JwtResposta nome(String nome) {
        setNome(nome);
        return this;
    }

    public JwtResposta roles(List<String> roles) {
        setRoles(roles);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JwtResposta)) {
            return false;
        }
        JwtResposta jwtResposta = (JwtResposta) o;
        return Objects.equals(token, jwtResposta.token) && Objects.equals(type, jwtResposta.type) && Objects.equals(id, jwtResposta.id) && Objects.equals(login, jwtResposta.login) && Objects.equals(email, jwtResposta.email) && Objects.equals(nome, jwtResposta.nome) && Objects.equals(roles, jwtResposta.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type, id, login, email, nome, roles);
    }

    @Override
    public String toString() {
        return "{" +
            " token='" + getToken() + "'" +
            ", type='" + getType() + "'" +
            ", id='" + getId() + "'" +
            ", login='" + getLogin() + "'" +
            ", email='" + getEmail() + "'" +
            ", nome='" + getNome() + "'" +
            ", roles='" + getRoles() + "'" +
            "}";
    }

}
