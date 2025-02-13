package com.pdcase.hospital.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pdcase.hospital.entities.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl {

    //application.properties
    @Value("${api.security.token.secret}")
    private String segredo;

    public String gerarToken(Users users){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo); // padrao de cryptografia
            //withIssuer - Define o emissor do token (quem gerou), pode ser o nome que quiser
            //withIssuer -  Define o dono do token (o login do usuário)
            //withExpiresAt - tempo de expiracao do token
            //sigin - Assina o token usando a chave secreta.
            String token = JWT.create()
                            .withIssuer("hospital-api")
                            .withSubject(users.getLogin())
                            .withExpiresAt(geraDataExpiracao())
                            .sign(algorithm);

            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("erro ao gerar o token", e);
        }
    }

    private Instant geraDataExpiracao(){
        // pega o tempo do acesso e expira em mais 2 horas, tranforma em um instante e coloca no horario de brasilia
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validaToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            return JWT.require(algorithm)
                    .withIssuer("hospital-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }
}
