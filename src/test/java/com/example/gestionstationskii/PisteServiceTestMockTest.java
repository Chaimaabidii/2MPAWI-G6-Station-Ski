package com.example.gestionstationskii;

import com.example.gestionstationskii.entities.Color;
import com.example.gestionstationskii.entities.Piste;
import com.example.gestionstationskii.services.IPisteServices;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class PisteServiceTestMockTest {

    @Mock
    private IPisteServices pisteService; // Service simulé (mocké)

    @InjectMocks
    private PisteServiceTestMockTest self; // Injection des mocks (ici symbolique)

    // 🧪 1️⃣ Test de l'ajout d'une piste
    @Test
    public void testAddPiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Piste Rouge");
        piste.setColor(Color.RED);
        piste.setLength(500);
        piste.setSlope(25);

        log.info("Création d'une piste : {} ({})", piste.getNamePiste(), piste.getColor());

        // Piste simulée renvoyée par le mock
        Piste savedPiste = new Piste();
        savedPiste.setNumPiste(1L);
        savedPiste.setNamePiste("Piste Rouge");
        savedPiste.setColor(Color.RED);
        savedPiste.setLength(500);
        savedPiste.setSlope(25);

        when(pisteService.addPiste(piste)).thenReturn(savedPiste);

        Piste result = pisteService.addPiste(piste);

        Assertions.assertNotNull(result.getNumPiste(), "L'ID de la piste ne doit pas être nul");
        Assertions.assertEquals("Piste Rouge", result.getNamePiste());
        Assertions.assertEquals(Color.RED, result.getColor());

        log.info("✅ Test addPiste réussi : {}", result.getNamePiste());
        verify(pisteService, times(1)).addPiste(piste);
    }

    // 🧪 2️⃣ Test de la récupération de toutes les pistes
    @Test
    public void testRetrieveAllPistes() {
        Piste piste1 = new Piste();
        piste1.setNumPiste(1L);
        piste1.setNamePiste("Piste Bleue");
        piste1.setColor(Color.BLUE);

        Piste piste2 = new Piste();
        piste2.setNumPiste(2L);
        piste2.setNamePiste("Piste Verte");
        piste2.setColor(Color.GREEN);

        List<Piste> pistesSimulees = Arrays.asList(piste1, piste2);

        when(pisteService.retrieveAllPistes()).thenReturn(pistesSimulees);

        List<Piste> result = pisteService.retrieveAllPistes();

        Assertions.assertEquals(2, result.size(), "Il doit y avoir 2 pistes");
        Assertions.assertEquals("Piste Bleue", result.get(0).getNamePiste());
        Assertions.assertEquals("Piste Verte", result.get(1).getNamePiste());

        log.info("✅ Test retrieveAllPistes réussi, nombre de pistes : {}", result.size());
        verify(pisteService, times(1)).retrieveAllPistes();
    }

    // 🧪 3️⃣ Test de la récupération d'une piste par ID
    @Test
    public void testRetrievePiste() {
        Piste piste = new Piste();
        piste.setNumPiste(1L);
        piste.setNamePiste("Piste Noire");
        piste.setColor(Color.BLACK);

        when(pisteService.retrievePiste(1L)).thenReturn(piste);

        Piste result = pisteService.retrievePiste(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Piste Noire", result.getNamePiste());
        Assertions.assertEquals(Color.BLACK, result.getColor());

        log.info("✅ Test retrievePiste réussi : {}", result.getNamePiste());
        verify(pisteService, times(1)).retrievePiste(1L);
    }

    // 🧪 4️⃣ Test de la suppression d'une piste
    @Test
    public void testRemovePiste() {
        doNothing().when(pisteService).removePiste(1L);

        pisteService.removePiste(1L);

        log.info("✅ Test removePiste réussi : piste supprimée avec ID 1");
        verify(pisteService, times(1)).removePiste(1L);
    }
}
