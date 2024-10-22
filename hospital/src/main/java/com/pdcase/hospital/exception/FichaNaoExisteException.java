package com.pdcase.hospital.exception;

public class FichaNaoExisteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FichaNaoExisteException(String mensagem) {
        super(mensagem);
    }
}