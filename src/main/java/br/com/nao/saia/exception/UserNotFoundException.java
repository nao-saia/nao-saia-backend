package br.com.nao.saia.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5358771253298177011L;

	public UserNotFoundException(UUID id) {
        super(String.format("User not found with id %s", id));
    }

}
