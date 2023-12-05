package fr.example.authentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.example.authentservice.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByUsername(String username);
}
