package br.com.nao.saia.exception;

public class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7917122532401576407L;

	public CityNotFoundException(Integer id) {
        super(String.format("City not found with id %s", id));
    }
}
