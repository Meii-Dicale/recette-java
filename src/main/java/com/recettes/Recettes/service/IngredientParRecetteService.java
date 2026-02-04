package com.recettes.Recettes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.recettes.Recettes.dto.IngredientParRecetteRequestDto;
import com.recettes.Recettes.dto.IngredientParRecetteResponse;
import com.recettes.Recettes.entities.Ingredient;
import com.recettes.Recettes.entities.IngredientParRecette;
import com.recettes.Recettes.entities.Recette;
import com.recettes.Recettes.mappers.IngredientParRecetteMapper;
import com.recettes.Recettes.repository.IngredientParRecetteRepo;
import com.recettes.Recettes.repository.IngredientRepo;
import com.recettes.Recettes.repository.RecetteRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientParRecetteService {
    private final IngredientParRecetteRepo ingredientParRecetteRepo;
    private final IngredientParRecetteMapper mapper;
    private final RecetteRepo recetteRepo;
    private final IngredientRepo ingredientRepo;
    
    public IngredientParRecetteResponse createIngredientParRecette(IngredientParRecetteRequestDto dto) {
        IngredientParRecette ingredientParRecette = mapper.toEntity(dto);
        Recette recette = recetteRepo.findById(dto.getRecetteId())
            .orElseThrow(() -> new RuntimeException("Recette not found"));
        Ingredient ingredient = ingredientRepo.findById(dto.getIngredientId())
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        ingredientParRecette.setRecette(recette);
        ingredientParRecette.setIngredient(ingredient);
        IngredientParRecette saved = ingredientParRecetteRepo.save(ingredientParRecette);
        return mapper.toResponse(saved);
    }
    
    public List<IngredientParRecetteResponse> getIngredientsByRecette(Long recetteId) {
        return ingredientParRecetteRepo.findByRecetteId(recetteId).stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public void deleteIngredientParRecette(Long id) {
        ingredientParRecetteRepo.deleteById(id);
    }
}
