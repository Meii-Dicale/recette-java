package com.recettes.Recettes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recettes.Recettes.dto.UserRequestDto;
import com.recettes.Recettes.dto.UserResponse;
import com.recettes.Recettes.entities.User;
import com.recettes.Recettes.mappers.UserMapper;
import com.recettes.Recettes.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    
    public UserResponse createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return userMapper.toResponse(savedUser);
    }
    
    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
            .map(userMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public Optional<UserResponse> getUserById(Long id) {
        return userRepo.findById(id)
            .map(userMapper::toResponse);
    }
    
    public UserResponse updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setNom(userRequestDto.getNom());
        user.setPrenom(userRequestDto.getPrenom());
        user.setRole(userRequestDto.getRole());
        if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        user.setTelephone(userRequestDto.getTelephone());
        user.setMail(userRequestDto.getMail());
        User savedUser = userRepo.save(user);
        return userMapper.toResponse(savedUser);
    }
    
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
    
    public Optional<User> findByMail(String mail) {
        return userRepo.findByMail(mail);
    }
}
