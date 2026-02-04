package com.recettes.Recettes.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.recettes.Recettes.dto.IngredientParRecetteResponse;
import com.recettes.Recettes.entities.Recette;
import com.recettes.Recettes.repository.IngredientParRecetteRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExportService {
    
    private final IngredientParRecetteRepo ingredientParRecetteRepo;
    
    public byte[] exportRecetteToPdf(Recette recette) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        
        // Titre
        Paragraph title = new Paragraph(recette.getNom())
            .setFontSize(20)
            .setBold()
            .setTextAlignment(TextAlignment.CENTER)
            .setMarginBottom(20);
        document.add(title);
        
        // Informations générales
        document.add(new Paragraph("Durée de préparation: " + recette.getDureePreparation() + " minutes"));
        document.add(new Paragraph("Durée de cuisson: " + recette.getDureeCuisson() + " minutes"));
        document.add(new Paragraph("Calories: " + recette.getNombreCalorique()));
        document.add(new Paragraph("Partagée: " + (recette.getPartage() ? "Oui" : "Non")));
        document.add(new Paragraph("\n"));
        
        // Ingrédients
        List<IngredientParRecetteResponse> ingredients = ingredientParRecetteRepo
            .findByRecetteId(recette.getId())
            .stream()
            .map(ipr -> {
                IngredientParRecetteResponse response = new IngredientParRecetteResponse();
                response.setId(ipr.getId());
                response.setIngredientLibelle(ipr.getIngredient().getLibelle());
                response.setQuantite(ipr.getQuantite());
                return response;
            })
            .toList();
        
        if (!ingredients.isEmpty()) {
            document.add(new Paragraph("Ingrédients:").setBold().setMarginTop(10));
            
            Table table = new Table(UnitValue.createPercentArray(new float[]{3, 1}));
            table.addHeaderCell("Ingrédient");
            table.addHeaderCell("Quantité");
            
            for (IngredientParRecetteResponse ing : ingredients) {
                table.addCell(ing.getIngredientLibelle());
                table.addCell(ing.getQuantite().toString());
            }
            
            document.add(table);
        }
        
        document.close();
        return baos.toByteArray();
    }
    
    public byte[] exportRecetteToXlsx(Recette recette) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Recette");
        
        int rowNum = 0;
        
        // Titre
        Row titleRow = sheet.createRow(rowNum++);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(recette.getNom());
        
        // Informations
        Row infoRow1 = sheet.createRow(rowNum++);
        infoRow1.createCell(0).setCellValue("Durée de préparation (minutes)");
        infoRow1.createCell(1).setCellValue(recette.getDureePreparation());
        
        Row infoRow2 = sheet.createRow(rowNum++);
        infoRow2.createCell(0).setCellValue("Durée de cuisson (minutes)");
        infoRow2.createCell(1).setCellValue(recette.getDureeCuisson());
        
        Row infoRow3 = sheet.createRow(rowNum++);
        infoRow3.createCell(0).setCellValue("Calories");
        infoRow3.createCell(1).setCellValue(recette.getNombreCalorique());
        
        Row infoRow4 = sheet.createRow(rowNum++);
        infoRow4.createCell(0).setCellValue("Partagée");
        infoRow4.createCell(1).setCellValue(recette.getPartage() ? "Oui" : "Non");
        
        rowNum++; // Ligne vide
        
        // En-têtes des ingrédients
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Ingrédient");
        headerRow.createCell(1).setCellValue("Quantité");
        
        // Ingrédients
        List<IngredientParRecetteResponse> ingredients = ingredientParRecetteRepo
            .findByRecetteId(recette.getId())
            .stream()
            .map(ipr -> {
                IngredientParRecetteResponse response = new IngredientParRecetteResponse();
                response.setId(ipr.getId());
                response.setIngredientLibelle(ipr.getIngredient().getLibelle());
                response.setQuantite(ipr.getQuantite());
                return response;
            })
            .toList();
        
        for (IngredientParRecetteResponse ing : ingredients) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(ing.getIngredientLibelle());
            row.createCell(1).setCellValue(ing.getQuantite());
        }
        
        // Ajuster la largeur des colonnes
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();
        
        return baos.toByteArray();
    }
}
