package com.example.gestionstationskii.controllers;

import com.example.gestionstationskii.entities.Skier;
import com.example.gestionstationskii.services.ISkierServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SkierRestControllerSimpleTest {

    @Mock
    private ISkierServices skierServices;

    @InjectMocks
    private SkierRestController skierRestController;

    private Skier skier;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        skier = new Skier();
        skier.setNumSkier(1L);
        skier.setFirstName("Mayssa");
    }

    @Test
    void testAddSkier() {
        // Arrange
        when(skierServices.addSkier(any(Skier.class))).thenReturn(skier);

        // Act
        Skier result = skierRestController.addSkier(skier);

        // Assert
        assertNotNull(result);
        assertEquals("Mayssa", result.getFirstName());
        verify(skierServices, times(1)).addSkier(any(Skier.class));
    }
}
