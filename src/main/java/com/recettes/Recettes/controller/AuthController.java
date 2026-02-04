package com.recettes.Recettes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recettes.Recettes.dto.LoginRequest;
import com.recettes.Recettes.dto.LoginResponse;
import com.recettes.Recettes.dto.UserRequestDto;
import com.recettes.Recettes.dto.UserResponse;
import com.recettes.Recettes.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.register(userRequestDto));
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
