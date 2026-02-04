package com.recettes.Recettes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecetteResponse {
    private Long id;
    private String nom;
    private Integer dureePreparation;
    private Integer dureeCuisson;
    private Integer nombreCalorique;
    private Boolean partage;
    private Long userId;
    private String userNom;
}
