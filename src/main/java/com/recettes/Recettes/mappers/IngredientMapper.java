package com.recettes.Recettes.mappers;

import org.mapstruct.Mapper;

import com.recettes.Recettes.dto.IngredientRequestDto;
import com.recettes.Recettes.dto.IngredientResponse;
import com.recettes.Recettes.entities.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientRequestDto toDto(Ingredient ingredient);
    IngredientResponse toResponse(Ingredient ingredient);
    Ingredient toEntity(IngredientRequestDto ingredientRequestDto);
}
