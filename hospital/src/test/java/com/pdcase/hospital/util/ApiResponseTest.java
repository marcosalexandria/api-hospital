package com.pdcase.hospital.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ApiResponseTest {

    @Test
    public void testApiResponseConstructorAndGetters() {
        // Arrange
        String mensagem = "Operação bem-sucedida";
        String dados = "Dados de exemplo";

        // Act
        ApiResponse response = new ApiResponse(mensagem, dados);

        // Assert
        assertThat(response.getMensagem()).isEqualTo(mensagem);
        assertThat(response.getDados()).isEqualTo(dados);
    }

    @Test
    public void testApiResponseSetters() {
        // Arrange
        String mensagem = "Erro ao processar";
        String dados = "Erro específico";

        ApiResponse response = new ApiResponse("", null);

        // Act
        response.setMensagem(mensagem);
        response.setDados(dados);

        // Assert
        assertThat(response.getMensagem()).isEqualTo(mensagem);
        assertThat(response.getDados()).isEqualTo(dados);
    }

    @Test
    public void testApiResponseDefaultValues() {
        // Act
        ApiResponse response = new ApiResponse(null, null);

        // Assert
        assertThat(response.getMensagem()).isNull();
        assertThat(response.getDados()).isNull();
    }

    @Test
    public void testApiResponseWithDifferentTypes() {
        // Arrange
        String mensagem = "Dados variados";
        Integer dados = 123;

        // Act
        ApiResponse response = new ApiResponse(mensagem, dados);

        // Assert
        assertThat(response.getMensagem()).isEqualTo(mensagem);
        assertThat(response.getDados()).isEqualTo(dados);
    }
}