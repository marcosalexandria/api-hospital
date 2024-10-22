package com.pdcase.hospital.util;

public class ApiResponse {
    
    private String mensagem;
    private Object dados;

    public ApiResponse(String mensagem, Object dados) {
        this.mensagem = mensagem;
        this.dados = dados;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }
}
