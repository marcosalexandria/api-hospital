package com.pdcase.hospital.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ResultadoNaoEncontradoExceptionTest {

    @Test
    public void testResultadoNaoEntontradoException() {
        String mensagemEsperada = "Resultado não encontrado";

        ResultadoNaoEntontradoException exception = assertThrows(ResultadoNaoEntontradoException.class, () -> {
            throw new ResultadoNaoEntontradoException(mensagemEsperada);
        });

        assertEquals(mensagemEsperada, exception.getMessage());
    }
}