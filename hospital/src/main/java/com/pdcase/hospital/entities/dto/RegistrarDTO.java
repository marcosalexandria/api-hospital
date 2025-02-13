package com.pdcase.hospital.entities.dto;

import com.pdcase.hospital.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarDTO {
    private String login;
    private String password;
    private UserRole role;
}
