package br.com.nao.saia.exception;

public class PasswordInvalidException extends RuntimeException {

	public PasswordInvalidException() {
        super("Password invalid!");
    }

}
