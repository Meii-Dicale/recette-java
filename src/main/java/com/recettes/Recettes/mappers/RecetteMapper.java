package com.recettes.Recettes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recettes.Recettes.dto.RecetteRequestDto;
import com.recettes.Recettes.dto.RecetteResponse;
import com.recettes.Recettes.entities.Recette;

@Mapper(componentModel = "spring")
public interface RecetteMapper {
    RecetteRequestDto toDto(Recette recette);
    
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userNom", expression = "java(recette.getUser() != null ? recette.getUser().getNom() : null)")
    RecetteResponse toResponse(Recette recette);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "usersFavoris", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    Recette toEntity(RecetteRequestDto recetteRequestDto);
}
