package com.recettes.Recettes.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recettes")
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Integer dureePreparation;
    private Integer dureeCuisson;
    private Integer nombreCalorique;
    private Boolean partage;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToMany(mappedBy = "recettesFavorites")
    private Set<User> usersFavoris;
    
    @OneToMany(mappedBy = "recette")
    private Set<IngredientParRecette> ingredients = new java.util.HashSet<>();
}
