package com.recettes.Recettes.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.recettes.Recettes.dto.LoginRequest;
import com.recettes.Recettes.dto.LoginResponse;
import com.recettes.Recettes.dto.UserRequestDto;
import com.recettes.Recettes.dto.UserResponse;
import com.recettes.Recettes.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getMail(),
                loginRequest.getPassword()
            )
        );
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getMail());
        String token = jwtService.generateToken(userDetails);
        
        User user = userService.findByMail(loginRequest.getMail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setRole(user.getRole());
        response.setNom(user.getNom());
        response.setPrenom(user.getPrenom());
        
        return response;
    }
    
    public UserResponse register(UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }
}
