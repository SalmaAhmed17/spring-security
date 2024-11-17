package com.example.springSecurity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepo extends JpaRepository<users, Long> {
    Optional<users> findByUsername(String username);
}

