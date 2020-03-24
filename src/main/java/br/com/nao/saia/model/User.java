package br.com.nao.saia.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidade que representa Usuario
 *
 * @author Taynan Rezende
 * @since 22/03/2020
 */
@Document(collection = "users")
public class User extends EntitySupport {

	@NotNull(message="Email do usuário é obrigatório")
	private String email;
	@NotNull(message="Nome do usuário é obrigatório")
	private String name;
	@NotNull(message="Senha do usuário é obrigatória")
	private String password;
	@AssertTrue(message = "Usuário não aceitou termos de compromisso e privacidade")
	private boolean acceptTerms;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAcceptTerms() {
		return acceptTerms;
	}

	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}

	public void hidePass() {
		this.password = null;
	}

}
