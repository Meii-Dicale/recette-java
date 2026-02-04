package com.recettes.Recettes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.recettes.Recettes.dto.IngredientRequestDto;
import com.recettes.Recettes.dto.IngredientResponse;
import com.recettes.Recettes.entities.Ingredient;
import com.recettes.Recettes.mappers.IngredientMapper;
import com.recettes.Recettes.repository.IngredientRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepo ingredientRepo;
    private final IngredientMapper ingredientMapper;
    
    public IngredientResponse createIngredient(IngredientRequestDto ingredientRequestDto) {
        Ingredient ingredient = ingredientMapper.toEntity(ingredientRequestDto);
        Ingredient savedIngredient = ingredientRepo.save(ingredient);
        return ingredientMapper.toResponse(savedIngredient);
    }
    
    public List<IngredientResponse> getAllIngredients() {
        return ingredientRepo.findAll().stream()
            .map(ingredientMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public Optional<IngredientResponse> getIngredientById(Long id) {
        return ingredientRepo.findById(id)
            .map(ingredientMapper::toResponse);
    }
    
    public IngredientResponse updateIngredient(Long id, IngredientRequestDto ingredientRequestDto) {
        Ingredient ingredient = ingredientRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        ingredient.setLibelle(ingredientRequestDto.getLibelle());
        ingredient.setType(ingredientRequestDto.getType());
        ingredient.setNombreCalorique(ingredientRequestDto.getNombreCalorique());
        Ingredient savedIngredient = ingredientRepo.save(ingredient);
        return ingredientMapper.toResponse(savedIngredient);
    }
    
    public void deleteIngredient(Long id) {
        ingredientRepo.deleteById(id);
    }
}
