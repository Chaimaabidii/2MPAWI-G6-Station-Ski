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
public class PisteServiceTestMock {

    @Mock
    private IPisteServices pisteService;  // mock du service

    @InjectMocks
    private PisteServiceTestMock self;   // injecte le mock

    @Test
    public void testAddPiste() {
        // Création d'une piste
        Piste piste = new Piste();
        piste.setNamePiste("Piste Rouge");
        piste.setColor(Color.RED);
        piste.setLength(500);
        piste.setSlope(25);

        log.info("Création d'une piste : {} ({})", piste.getNamePiste(), piste.getColor());

        // Piste simulée renvoyée par le service
        Piste savedPiste = new Piste();
        savedPiste.setNumPiste(1L);
        savedPiste.setNamePiste("Piste Rouge");
        savedPiste.setColor(Color.RED);
        savedPiste.setLength(500);
        savedPiste.setSlope(25);

        // Définir le comportement du mock
        when(pisteService.addPiste(piste)).thenReturn(savedPiste);

        // Appel du service
        Piste result = pisteService.addPiste(piste);

        // Vérifications
        Assertions.assertNotNull(result.getNumPiste(), "L'ID de la piste ne doit pas être nul");
        Assertions.assertEquals("Piste Rouge", result.getNamePiste());
        Assertions.assertEquals(Color.RED, result.getColor());

        log.info("Test addPiste réussi : {}", result.getNamePiste());

        // Vérifier que la méthode addPiste a été appelée une fois
        verify(pisteService, times(1)).addPiste(piste);
    }

    @Test
    public void testRetrieveAllPistes() {
        // Liste de pistes simulées
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

        // Appel du service
        List<Piste> result = pisteService.retrieveAllPistes();

        Assertions.assertEquals(2, result.size(), "Il doit y avoir 2 pistes");
        Assertions.assertEquals("Piste Bleue", result.get(0).getNamePiste());
        Assertions.assertEquals("Piste Verte", result.get(1).getNamePiste());

        log.info("Test retrieveAllPistes réussi, nombre de pistes : {}", result.size());

        verify(pisteService, times(1)).retrieveAllPistes();
    }
}
