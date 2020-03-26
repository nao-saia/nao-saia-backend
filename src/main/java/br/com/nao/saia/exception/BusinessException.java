package br.com.nao.saia.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -382550284849642454L;

	public BusinessException(String message) {
		super(message);
	}

}
