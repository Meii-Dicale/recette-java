package com.recettes.Recettes.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final UserMapper userMapper;
    public UserResponse createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        User savedUser = userRepo.save(user);
        return userMapper.toResponse(savedUser);
    }

    
}
