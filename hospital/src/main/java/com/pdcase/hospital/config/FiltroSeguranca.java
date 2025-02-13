package com.pdcase.hospital.config;

import com.pdcase.hospital.repositories.UserRepository;
import com.pdcase.hospital.services.impl.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {
    // OncePerRequestFilter: garante que o filtro será executado apenas uma vez por requisição.

    @Autowired
    TokenServiceImpl service;

    @Autowired
    UserRepository repository;

    // Metodo principal do filtro, chamado automaticamente pelo Spring para cada requisição.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuperarToken(request); // Obtém o token do cabeçalho da requisição
        if (token != null){
            var login = service.validaToken(token);
            UserDetails userDetails = repository.findByLogin(login);

            var autenticacao = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //faz a verificacao
            SecurityContextHolder.getContext().setAuthentication(autenticacao); //salva as informacoes no contexto da autenticacao
        }
        filterChain.doFilter(request, response); //acaba e pula para o proximo filtro
    }

    private String recuperarToken(HttpServletRequest request) {
        // Metodo que extrai o token JWT do cabeçalho Authorization

        var authHeader = request.getHeader("Authorization"); // Pega o valor do cabeçalho Authorization

        if (authHeader == null) return null; // Se não houver cabeçalho Authorization, retorna null

        return authHeader.replace("Bearer ", "");
        // Remove o prefixo "Bearer " do token (tokens JWT normalmente vêm no formato "Bearer <token>")
    }
}
