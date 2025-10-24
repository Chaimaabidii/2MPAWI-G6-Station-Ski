package com.example.gestionstationskii.services;

import com.example.gestionstationskii.entities.Piste;
import com.example.gestionstationskii.repositories.IPisteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PisteServicesImpl implements IPisteServices {

    private final IPisteRepository pisteRepository;

    @Override
    public Piste addPiste(Piste piste) {
        Piste saved = pisteRepository.save(piste);
        System.out.println("✅ Nouvelle piste ajoutée : " + saved.getNamePiste() + " (" + saved.getColor() + ")");
        return saved;
    }

    @Override
    public List<Piste> retrieveAllPistes() {
        System.out.println("📋 Récupération de toutes les pistes...");
        return pisteRepository.findAll();
    }

    @Override
    public Piste retrievePiste(Long numPiste) {
        Piste piste = pisteRepository.findById(numPiste)
                .orElseThrow(() -> new RuntimeException("❌ Piste non trouvée avec l'id : " + numPiste));
        System.out.println("🔎 Piste trouvée : " + piste.getNamePiste());
        return piste;
    }

    @Override
    public void removePiste(Long numPiste) {
        pisteRepository.deleteById(numPiste);
        System.out.println("🗑️ Piste supprimée avec succès : " + numPiste);
    }
}
