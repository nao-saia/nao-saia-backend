package br.com.nao.saia.exception;

import java.util.UUID;

public class ContributorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4007640986383724946L;

	public ContributorNotFoundException(UUID id) {
        super(String.format("Contributor not found with id %s", id));
    }
}
