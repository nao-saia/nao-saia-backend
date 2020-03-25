package br.com.nao.saia.exception;

import java.util.UUID;

public class MerchantNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -739084194869614887L;

	public MerchantNotFoundException(UUID id) {
        super(String.format("Merchant not found with id %s", id));
    }
}
