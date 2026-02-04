package com.recettes.Recettes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recettes.Recettes.entities.IngredientParRecette;

@Repository
public interface IngredientParRecetteRepo extends JpaRepository<IngredientParRecette, Long> {
    List<IngredientParRecette> findByRecetteId(Long recetteId);
}
