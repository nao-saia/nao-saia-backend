package br.com.nao.saia.exception;

public class StateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 9117600442700073122L;

	public StateNotFoundException(Integer id) {
        super(String.format("State not found with id %s", id));
    }
}
