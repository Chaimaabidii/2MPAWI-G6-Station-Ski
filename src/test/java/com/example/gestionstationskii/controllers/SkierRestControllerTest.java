package com.example.gestionstationskii.controllers;

import com.example.gestionstationskii.entities.Skier;
import com.example.gestionstationskii.entities.TypeSubscription;
import com.example.gestionstationskii.services.ISkierServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkierRestControllerTest {

    @Mock
    private ISkierServices skierServices;

    @InjectMocks
    private SkierRestController skierRestController;

    private Skier skier;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        skier = new Skier();
        skier.setNumSkier(1L);
        skier.setFirstName("Mayssa");
        skier.setLastName("Arfaoui");
    }

    @Test
    void testAddSkier() {
        when(skierServices.addSkier(any(Skier.class))).thenReturn(skier);

        Skier result = skierRestController.addSkier(skier);

        assertNotNull(result);
        assertEquals("Mayssa", result.getFirstName());
        verify(skierServices, times(1)).addSkier(any(Skier.class));
    }

    @Test
    void testGetAllSkiers() {
        when(skierServices.retrieveAllSkiers()).thenReturn(Arrays.asList(skier));

        List<Skier> result = skierRestController.getAllSkiers();

        assertEquals(1, result.size());
        verify(skierServices, times(1)).retrieveAllSkiers();
    }

    @Test
    void testGetById() {
        when(skierServices.retrieveSkier(1L)).thenReturn(skier);

        Skier result = skierRestController.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getNumSkier().longValue());
        verify(skierServices, times(1)).retrieveSkier(1L);
    }

    @Test
    void testRetrieveSkiersBySubscriptionType() {
        when(skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL))
                .thenReturn(Arrays.asList(skier));

        List<Skier> result = skierRestController.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        assertFalse(result.isEmpty());
        verify(skierServices, times(1)).retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);
    }

    @Test
    void testDeleteById() {
        skierRestController.deleteById(1L);

        verify(skierServices, times(1)).removeSkier(1L);
    }
}
