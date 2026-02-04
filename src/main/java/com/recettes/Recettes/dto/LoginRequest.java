package com.recettes.Recettes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String mail;
    private String password;
}
