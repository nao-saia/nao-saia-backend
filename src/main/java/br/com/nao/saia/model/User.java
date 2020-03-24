package br.com.nao.saia.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidade que representa Usuario
 *
 * @author Taynan Rezende
 * @since 22/03/2020
 */
@Document(collection = "users")
public class User extends EntitySupport {

	private String email;
	private String name;
	private String password;
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



}
