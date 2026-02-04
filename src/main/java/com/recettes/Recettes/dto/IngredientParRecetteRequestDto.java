package com.recettes.Recettes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientParRecetteRequestDto {
    private Long recetteId;
    private Long ingredientId;
    private Integer quantite;
}
