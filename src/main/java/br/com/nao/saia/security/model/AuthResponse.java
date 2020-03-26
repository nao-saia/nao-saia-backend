package br.com.nao.saia.security.model;

import br.com.nao.saia.model.User;

public class AuthResponse {

	private String token;
	private User user;

	public AuthResponse(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}

}