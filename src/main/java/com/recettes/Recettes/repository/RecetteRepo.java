package com.recettes.Recettes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recettes.Recettes.entities.Recette;

@Repository
public interface RecetteRepo extends JpaRepository<Recette, Long> {
    List<Recette> findByPartageTrue();
    List<Recette> findByUserId(Long userId);
}
