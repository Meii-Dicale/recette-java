package com.recettes.Recettes.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private enum Type {
        VIANDE,
        FRUIT,
        LEGUME,
        EPICE,
        BOISSON,
        AUTRE
    }
    private Type type;
    private Integer nombreCalorique;
}
