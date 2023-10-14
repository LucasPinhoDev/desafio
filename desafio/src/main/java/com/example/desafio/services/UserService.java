package com.example.desafio.services;

import com.example.desafio.entities.UserEntity;
import com.example.desafio.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity updateUser(UUID id, UserEntity updatedUser) {
        Optional<UserEntity> existingUserOptional = userRepository.findById(id);
        if (!existingUserOptional.isPresent()) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }

        UserEntity existingUser = existingUserOptional.get();

        if (updatedUser.getNome() != null) {
            existingUser.setNome(updatedUser.getNome());
        }
        if (updatedUser.getIdade() != 0) {
            existingUser.setIdade(updatedUser.getIdade());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        return userRepository.save(existingUser);
    }

    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
