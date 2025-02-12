package com.pdcase.hospital.repositories;

import com.pdcase.hospital.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    //UserDetails vai ser gerenciado pelo spring security
    UserDetails findByLogin(String login);
}
