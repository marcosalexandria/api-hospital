package com.pdcase.hospital.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FichaJaExisteExceptionTest {

    @Test
    public void testFichaJaExisteException() {
        String mensagemEsperada = "Ficha já existe";

        FichaJaExisteException exception = assertThrows(FichaJaExisteException.class, () -> {
            throw new FichaJaExisteException(mensagemEsperada);
        });

        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
