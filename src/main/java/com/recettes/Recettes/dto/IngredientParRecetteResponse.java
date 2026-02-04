package com.recettes.Recettes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientParRecetteResponse {
    private Long id;
    private Long recetteId;
    private String recetteNom;
    private Long ingredientId;
    private String ingredientLibelle;
    private Integer quantite;
}
