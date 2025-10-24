package com.example.gestionstationskii;

import com.example.gestionstationskii.entities.Color;
import com.example.gestionstationskii.entities.Piste;
import com.example.gestionstationskii.repositories.IPisteRepository;
import com.example.gestionstationskii.services.PisteServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PisteServiceImplTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;

    private Piste piste1;
    private Piste piste2;

    @BeforeEach
    public void setup() {
        piste1 = new Piste();
        piste1.setNumPiste(1L);
        piste1.setNamePiste("Piste Bleue");
        piste1.setColor(Color.BLUE);
        piste1.setLength(450);
        piste1.setSlope(20);

        piste2 = new Piste();
        piste2.setNumPiste(2L);
        piste2.setNamePiste("Piste Rouge");
        piste2.setColor(Color.RED);
        piste2.setLength(600);
        piste2.setSlope(30);
    }

    @Test
    public void testAddPiste() {
        when(pisteRepository.save(piste1)).thenReturn(piste1);

        Piste saved = pisteServices.addPiste(piste1);

        assertNotNull(saved);
        assertEquals("Piste Bleue", saved.getNamePiste());
        verify(pisteRepository, times(1)).save(piste1);
    }

    @Test
    public void testRetrieveAllPistes() {
        when(pisteRepository.findAll()).thenReturn(Arrays.asList(piste1, piste2));

        List<Piste> pistes = pisteServices.retrieveAllPistes();

        assertEquals(2, pistes.size());
        verify(pisteRepository, times(1)).findAll();
    }

    @Test
    public void testRetrievePiste() {
        when(pisteRepository.findById(1L)).thenReturn(Optional.of(piste1));

        Piste found = pisteServices.retrievePiste(1L);

        assertNotNull(found);
        assertEquals("Piste Bleue", found.getNamePiste());
        verify(pisteRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrievePiste_NotFound() {
        when(pisteRepository.findById(3L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pisteServices.retrievePiste(3L);
        });

        assertTrue(exception.getMessage().contains("Piste non trouvée"));
        verify(pisteRepository, times(1)).findById(3L);
    }

    @Test
    public void testRemovePiste() {
        doNothing().when(pisteRepository).deleteById(1L);

        pisteServices.removePiste(1L);

        verify(pisteRepository, times(1)).deleteById(1L);
    }
}
