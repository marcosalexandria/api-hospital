package com.pdcase.hospital.resources;

import com.pdcase.hospital.entities.Users;
import com.pdcase.hospital.entities.dto.AuthenticationDTO;
import com.pdcase.hospital.entities.dto.RegistrarDTO;
import com.pdcase.hospital.repositories.UserRepository;
import com.pdcase.hospital.services.impl.TokenServiceImpl;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationResource {

    @Autowired
    private AuthenticationManager authentication; //Gerencia a autenticação dos usuários com base nas configurações definidas.

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenServiceImpl service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword()); //diz ao Spring Security que queremos autenticar um usuário com essas credenciais.
        var auth = this.authentication.authenticate(userNamePassword); //verifica se o usuário e senha são válidos:

        var token = service.gerarToken((Users) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistrarDTO data){

        if(repository.findByLogin(data.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encriptandoPassword = new BCryptPasswordEncoder().encode(data.getPassword()); //cryptografa a senha
        Users user = new Users(data.getLogin(), encriptandoPassword, data.getRole());

        repository.save(user);

        return ResponseEntity.ok().build();
    }
}
