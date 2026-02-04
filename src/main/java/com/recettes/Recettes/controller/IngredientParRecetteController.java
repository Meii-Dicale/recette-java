package com.recettes.Recettes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recettes.Recettes.dto.IngredientParRecetteRequestDto;
import com.recettes.Recettes.dto.IngredientParRecetteResponse;
import com.recettes.Recettes.service.IngredientParRecetteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ingredient-par-recette")
@RequiredArgsConstructor
public class IngredientParRecetteController {
    
    private final IngredientParRecetteService service;
    
    @PostMapping
    public ResponseEntity<IngredientParRecetteResponse> createIngredientParRecette(
            @RequestBody IngredientParRecetteRequestDto dto) {
        return ResponseEntity.ok(service.createIngredientParRecette(dto));
    }
    
    @GetMapping("/recette/{recetteId}")
    public ResponseEntity<List<IngredientParRecetteResponse>> getIngredientsByRecette(
            @PathVariable Long recetteId) {
        return ResponseEntity.ok(service.getIngredientsByRecette(recetteId));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientParRecette(@PathVariable Long id) {
        service.deleteIngredientParRecette(id);
        return ResponseEntity.noContent().build();
    }
}
