package br.com.nao.saia.exception;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4007640986383724946L;

	public CategoryNotFoundException(UUID id) {
        super(String.format("Category not found with id %s", id));
    }
}
