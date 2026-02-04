package com.recettes.Recettes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Long userId;
    private String role;
    private String nom;
    private String prenom;
}
