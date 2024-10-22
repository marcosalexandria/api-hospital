package com.pdcase.hospital.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FichaNaoExisteExceptionTest {

    @Test
    public void testFichaNaoExisteException() {
        String mensagemEsperada = "Ficha não existe";

        FichaNaoExisteException exception = assertThrows(FichaNaoExisteException.class, () -> {
            throw new FichaNaoExisteException(mensagemEsperada);
        });

        assertEquals(mensagemEsperada, exception.getMessage());
    }
}