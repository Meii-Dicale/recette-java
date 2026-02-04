package com.recettes.Recettes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.recettes.Recettes.dto.RecetteRequestDto;
import com.recettes.Recettes.dto.RecetteResponse;
import com.recettes.Recettes.entities.Recette;
import com.recettes.Recettes.entities.User;
import com.recettes.Recettes.mappers.RecetteMapper;
import com.recettes.Recettes.repository.RecetteRepo;
import com.recettes.Recettes.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecetteService {
    private final RecetteRepo recetteRepo;
    private final RecetteMapper recetteMapper;
    private final UserRepo userRepo;
    
    public RecetteResponse createRecette(RecetteRequestDto recetteRequestDto) {
        Recette recette = recetteMapper.toEntity(recetteRequestDto);
        if (recetteRequestDto.getUserId() != null) {
            User user = userRepo.findById(recetteRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
            recette.setUser(user);
        }
        Recette savedRecette = recetteRepo.save(recette);
        return recetteMapper.toResponse(savedRecette);
    }
    
    public List<RecetteResponse> getAllRecettes() {
        return recetteRepo.findAll().stream()
            .map(recetteMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public List<RecetteResponse> getRecettesPartagees() {
        return recetteRepo.findByPartageTrue().stream()
            .map(recetteMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public List<RecetteResponse> getRecettesByUser(Long userId) {
        return recetteRepo.findByUserId(userId).stream()
            .map(recetteMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public Optional<RecetteResponse> getRecetteById(Long id) {
        return recetteRepo.findById(id)
            .map(recetteMapper::toResponse);
    }
    
    public RecetteResponse updateRecette(Long id, RecetteRequestDto recetteRequestDto) {
        Recette recette = recetteRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Recette not found"));
        recette.setNom(recetteRequestDto.getNom());
        recette.setDureePreparation(recetteRequestDto.getDureePreparation());
        recette.setDureeCuisson(recetteRequestDto.getDureeCuisson());
        recette.setNombreCalorique(recetteRequestDto.getNombreCalorique());
        recette.setPartage(recetteRequestDto.getPartage());
        if (recetteRequestDto.getUserId() != null) {
            User user = userRepo.findById(recetteRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
            recette.setUser(user);
        }
        Recette savedRecette = recetteRepo.save(recette);
        return recetteMapper.toResponse(savedRecette);
    }
    
    public void deleteRecette(Long id) {
        recetteRepo.deleteById(id);
    }
    
    public void addToFavorites(Long userId, Long recetteId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Recette recette = recetteRepo.findById(recetteId)
            .orElseThrow(() -> new RuntimeException("Recette not found"));
        user.getRecettesFavorites().add(recette);
        userRepo.save(user);
    }
    
    public void removeFromFavorites(Long userId, Long recetteId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Recette recette = recetteRepo.findById(recetteId)
            .orElseThrow(() -> new RuntimeException("Recette not found"));
        user.getRecettesFavorites().remove(recette);
        userRepo.save(user);
    }
}
