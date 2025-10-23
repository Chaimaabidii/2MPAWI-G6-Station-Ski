package com.example.gestionstationskii.services;

import com.example.gestionstationskii.entities.Piste;
import com.example.gestionstationskii.repositories.IPisteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PisteServicesImpl implements IPisteServices {

    private final IPisteRepository pisteRepository;

    @Override
    public Piste addPiste(Piste piste) {
        System.out.println("🚀 Ajout d'une nouvelle piste : " + piste.getNamePiste());
        return pisteRepository.save(piste);
    }

    @Override
    public List<Piste> retrieveAllPistes() {
        System.out.println("📋 Récupération de toutes les pistes...");
        return pisteRepository.findAll();
    }

    @Override
    public Piste retrievePiste(Long numPiste) {
        return pisteRepository.findById(numPiste)
                .orElseThrow(() -> new RuntimeException("❌ Piste non trouvée avec l'id : " + numPiste));
    }

    @Override
    public void removePiste(Long numPiste) {
        pisteRepository.deleteById(numPiste);
        System.out.println("🗑️ Piste supprimée avec succès : " + numPiste);
    }
}
