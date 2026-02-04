package com.recettes.Recettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recettes.Recettes.entities.Ingredient;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
}
