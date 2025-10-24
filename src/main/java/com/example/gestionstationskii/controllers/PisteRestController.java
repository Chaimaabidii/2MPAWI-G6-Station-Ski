package com.example.gestionstationskii.controllers;

import com.example.gestionstationskii.entities.Piste;
import com.example.gestionstationskii.services.IPisteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "🎿 Piste Management", description = "Endpoints for managing ski pistes")
@RestController
@RequestMapping("/piste")
@RequiredArgsConstructor
public class PisteRestController {

    private final IPisteServices pisteServices;

    // ➕ Ajouter une nouvelle piste
    @Operation(summary = "Add a new piste")
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addPiste(@RequestBody Piste piste) {
        Piste saved = pisteServices.addPiste(piste);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "✅ Piste ajoutée avec succès : " + saved.getNamePiste());
        response.put("piste", saved);

        return ResponseEntity.ok(response);
    }

    // 🔍 Récupérer toutes les pistes
    @Operation(summary = "Retrieve all pistes")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllPistes() {
        List<Piste> pistes = pisteServices.retrieveAllPistes();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "📋 Liste de toutes les pistes (" + pistes.size() + ")");
        response.put("pistes", pistes);

        return ResponseEntity.ok(response);
    }

    // 🔎 Récupérer une piste par son ID
    @Operation(summary = "Retrieve piste by ID")
    @GetMapping("/get/{id-piste}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable("id-piste") Long numPiste) {
        Piste piste = pisteServices.retrievePiste(numPiste);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "🔎 Piste trouvée avec succès !");
        response.put("piste", piste);

        return ResponseEntity.ok(response);
    }

    // ✏️ Mettre à jour une piste existante
    @Operation(summary = "Update an existing piste")
    @PutMapping("/update/{id-piste}")
    public ResponseEntity<Map<String, Object>> updatePiste(@PathVariable("id-piste") Long numPiste,
                                                           @RequestBody Piste updatedPiste) {
        Piste existingPiste = pisteServices.retrievePiste(numPiste);

        existingPiste.setNamePiste(updatedPiste.getNamePiste());
        existingPiste.setColor(updatedPiste.getColor());
        existingPiste.setLength(updatedPiste.getLength());
        existingPiste.setSlope(updatedPiste.getSlope());

        Piste saved = pisteServices.addPiste(existingPiste);
        System.out.println("✏️ Piste mise à jour : " + saved.getNamePiste());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "✏️ Piste mise à jour avec succès !");
        response.put("piste", saved);

        return ResponseEntity.ok(response);
    }

    // 🗑️ Supprimer une piste
    @Operation(summary = "Delete a piste by ID")
    @DeleteMapping("/delete/{id-piste}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable("id-piste") Long numPiste) {
        pisteServices.removePiste(numPiste);

        Map<String, String> response = new HashMap<>();
        response.put("message", "🗑️ Piste supprimée avec succès (ID: " + numPiste + ")");

        return ResponseEntity.ok(response);
    }
}
