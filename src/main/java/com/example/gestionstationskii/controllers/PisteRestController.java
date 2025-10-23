package com.example.gestionstationskii.controllers;

import com.example.gestionstationskii.entities.Piste;
import com.example.gestionstationskii.services.IPisteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "🎿 Piste Management", description = "Endpoints for managing ski pistes")
@RestController
@RequestMapping("/piste")
@RequiredArgsConstructor
public class PisteRestController {

    private final IPisteServices pisteServices;

    // ➕ Ajouter une nouvelle piste
    @Operation(summary = "Add a new piste")
    @PostMapping("/add")
    public Piste addPiste(@RequestBody Piste piste) {
        return pisteServices.addPiste(piste);
    }

    // 🔍 Récupérer toutes les pistes
    @Operation(summary = "Retrieve all pistes")
    @GetMapping("/all")
    public List<Piste> getAllPistes() {
        return pisteServices.retrieveAllPistes();
    }

    // 🔎 Récupérer une piste par son ID
    @Operation(summary = "Retrieve piste by ID")
    @GetMapping("/get/{id-piste}")
    public Piste getById(@PathVariable("id-piste") Long numPiste) {
        return pisteServices.retrievePiste(numPiste);
    }

    // ✏️ Mettre à jour une piste existante
    @Operation(summary = "Update an existing piste")
    @PutMapping("/update/{id-piste}")
    public Piste updatePiste(@PathVariable("id-piste") Long numPiste, @RequestBody Piste updatedPiste) {
        Piste existingPiste = pisteServices.retrievePiste(numPiste);

        existingPiste.setNamePiste(updatedPiste.getNamePiste());
        existingPiste.setColor(updatedPiste.getColor());
        existingPiste.setLength(updatedPiste.getLength());
        existingPiste.setSlope(updatedPiste.getSlope());

        return pisteServices.addPiste(existingPiste);
    }

    // 🗑️ Supprimer une piste
    @Operation(summary = "Delete a piste by ID")
    @DeleteMapping("/delete/{id-piste}")
    public String deleteById(@PathVariable("id-piste") Long numPiste) {
        pisteServices.removePiste(numPiste);
        return "🗑️ Piste supprimée avec succès (ID: " + numPiste + ")";
    }
}
