package com.example.apisecuritybasic.repository;

import com.example.apisecuritybasic.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Users findUsersById(Long id);
}
