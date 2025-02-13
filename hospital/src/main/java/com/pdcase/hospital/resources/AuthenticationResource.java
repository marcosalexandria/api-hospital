package com.pdcase.hospital.resources;

import com.pdcase.hospital.entities.Users;
import com.pdcase.hospital.entities.dto.AuthenticationDTO;
import com.pdcase.hospital.entities.dto.RegistrarDTO;
import com.pdcase.hospital.repositories.UserRepository;
import jakarta.validation.Valid;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword()); //diz ao Spring Security que queremos autenticar um usuário com essas credenciais.
        var auth = this.authentication.authenticate(userNamePassword); //verifica se o usuário e senha são válidos:
        return ResponseEntity.ok().build();
    }


}
