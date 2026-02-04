package com.recettes.Recettes.dto;

import com.recettes.Recettes.entities.Ingredient.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientResponse {
    private Long id;
    private String libelle;
    private Type type;
    private Integer nombreCalorique;
}
