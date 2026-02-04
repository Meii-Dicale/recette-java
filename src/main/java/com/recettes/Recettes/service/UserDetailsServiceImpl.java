package com.recettes.Recettes.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recettes.Recettes.entities.User;
import com.recettes.Recettes.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User userEntity = userRepo.findByMail(mail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with mail: " + mail));
        
        return org.springframework.security.core.userdetails.User.builder()
            .username(userEntity.getMail())
            .password(userEntity.getPassword())
            .roles(userEntity.getRole())
            .build();
    }
}
