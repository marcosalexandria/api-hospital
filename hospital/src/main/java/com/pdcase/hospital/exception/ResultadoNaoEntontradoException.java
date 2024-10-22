package com.pdcase.hospital.exception;

public class ResultadoNaoEntontradoException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResultadoNaoEntontradoException(String mensagem) {
        super(mensagem);
    }
}