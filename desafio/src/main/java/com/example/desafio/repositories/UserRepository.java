package com.example.desafio.repositories;

import com.example.desafio.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
