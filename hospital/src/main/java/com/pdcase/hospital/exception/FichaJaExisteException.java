package com.pdcase.hospital.exception;

public class FichaJaExisteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FichaJaExisteException(String mensagem) {
        super(mensagem);
    }

}