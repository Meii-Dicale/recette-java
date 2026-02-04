package com.recettes.Recettes.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recettes.Recettes.dto.RecetteRequestDto;
import com.recettes.Recettes.dto.RecetteResponse;
import com.recettes.Recettes.entities.Recette;
import com.recettes.Recettes.repository.RecetteRepo;
import com.recettes.Recettes.service.ExportService;
import com.recettes.Recettes.service.RecetteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recettes")
@RequiredArgsConstructor
public class RecetteController {
    
    private final RecetteService recetteService;
    private final ExportService exportService;
    private final RecetteRepo recetteRepo;
    
    @PostMapping
    public ResponseEntity<RecetteResponse> createRecette(@RequestBody RecetteRequestDto recetteRequestDto) {
        return ResponseEntity.ok(recetteService.createRecette(recetteRequestDto));
    }
    
    @GetMapping
    public ResponseEntity<List<RecetteResponse>> getAllRecettes() {
        return ResponseEntity.ok(recetteService.getAllRecettes());
    }
    
    @GetMapping("/public")
    public ResponseEntity<List<RecetteResponse>> getRecettesPartagees() {
        return ResponseEntity.ok(recetteService.getRecettesPartagees());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecetteResponse>> getRecettesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(recetteService.getRecettesByUser(userId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RecetteResponse> getRecetteById(@PathVariable Long id) {
        return recetteService.getRecetteById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RecetteResponse> updateRecette(
            @PathVariable Long id,
            @RequestBody RecetteRequestDto recetteRequestDto) {
        return ResponseEntity.ok(recetteService.updateRecette(id, recetteRequestDto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecette(@PathVariable Long id) {
        recetteService.deleteRecette(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{recetteId}/favorites/{userId}")
    public ResponseEntity<Void> addToFavorites(
            @PathVariable Long userId,
            @PathVariable Long recetteId) {
        recetteService.addToFavorites(userId, recetteId);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{recetteId}/favorites/{userId}")
    public ResponseEntity<Void> removeFromFavorites(
            @PathVariable Long userId,
            @PathVariable Long recetteId) {
        recetteService.removeFromFavorites(userId, recetteId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/export/pdf")
    public ResponseEntity<byte[]> exportRecetteToPdf(@PathVariable Long id) throws IOException {
        Recette recette = recetteRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Recette not found"));
        byte[] pdfBytes = exportService.exportRecetteToPdf(recette);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", recette.getNom() + ".pdf");
        
        return ResponseEntity.ok()
            .headers(headers)
            .body(pdfBytes);
    }
    
    @GetMapping("/{id}/export/xlsx")
    public ResponseEntity<byte[]> exportRecetteToXlsx(@PathVariable Long id) throws IOException {
        Recette recette = recetteRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Recette not found"));
        byte[] xlsxBytes = exportService.exportRecetteToXlsx(recette);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", recette.getNom() + ".xlsx");
        
        return ResponseEntity.ok()
            .headers(headers)
            .body(xlsxBytes);
    }
}
