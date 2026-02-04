package com.recettes.Recettes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String role;
    private String telephone;
    private String mail;
}
