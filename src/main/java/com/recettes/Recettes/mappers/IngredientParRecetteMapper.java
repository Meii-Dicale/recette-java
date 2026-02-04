package com.recettes.Recettes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recettes.Recettes.dto.IngredientParRecetteRequestDto;
import com.recettes.Recettes.dto.IngredientParRecetteResponse;
import com.recettes.Recettes.entities.IngredientParRecette;

@Mapper(componentModel = "spring")
public interface IngredientParRecetteMapper {
    @Mapping(target = "recetteId", source = "recette.id")
    @Mapping(target = "recetteNom", expression = "java(ingredientParRecette.getRecette() != null ? ingredientParRecette.getRecette().getNom() : null)")
    @Mapping(target = "ingredientId", source = "ingredient.id")
    @Mapping(target = "ingredientLibelle", expression = "java(ingredientParRecette.getIngredient() != null ? ingredientParRecette.getIngredient().getLibelle() : null)")
    IngredientParRecetteResponse toResponse(IngredientParRecette ingredientParRecette);
    
    @Mapping(target = "recette", ignore = true)
    @Mapping(target = "ingredient", ignore = true)
    IngredientParRecette toEntity(IngredientParRecetteRequestDto dto);
}
