package com.example.gestionstationskii;

import com.example.gestionstationskii.entities.Registration;
import com.example.gestionstationskii.entities.Skier;
import com.example.gestionstationskii.entities.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationMockitoTest {

    private Registration registration;
    private Skier mockSkier;
    private Course mockCourse;

    @BeforeEach
    void setUp() {
        // Création des objets simulés (mocks)
        mockSkier = Mockito.mock(Skier.class);
        mockCourse = Mockito.mock(Course.class);

        // Création de l’entité à tester
        registration = new Registration(1L, 12, mockSkier, mockCourse);
    }

    // ✅ 1️⃣ Vérifie la création de l’objet avec les mocks
    @Test
    void testRegistrationWithMocks() {
        assertEquals(1L, registration.getNumRegistration());
        assertEquals(12, registration.getNumWeek());
        assertEquals(mockSkier, registration.getSkier());
        assertEquals(mockCourse, registration.getCourse());
    }

    // ✅ 2️⃣ Simule le comportement des mocks et vérifie indirectement le résultat
    @Test
    void testMockBehavior() {
        when(mockSkier.toString()).thenReturn("MockedSkier");
        when(mockCourse.toString()).thenReturn("MockedCourse");

        String result = registration.toString();

        assertTrue(result.contains("MockedSkier"));
        assertTrue(result.contains("MockedCourse"));

        // ❌ Supprimé : on ne peut pas vérifier toString() directement sur les mocks
        // verify(mockSkier, times(1)).toString();
        // verify(mockCourse, times(1)).toString();
    }

    // ✅ 3️⃣ Change les relations (setters) et vérifie que le mock est bien remplacé
    @Test
    void testChangeMockRelations() {
        Skier newMockSkier = Mockito.mock(Skier.class);
        Course newMockCourse = Mockito.mock(Course.class);

        registration.setSkier(newMockSkier);
        registration.setCourse(newMockCourse);

        assertEquals(newMockSkier, registration.getSkier());
        assertEquals(newMockCourse, registration.getCourse());
    }

    // ✅ 4️⃣ Vérifie que l’objet reste cohérent après modifications
    @Test
    void testObjectConsistencyAfterUpdates() {
        registration.setNumWeek(20);
        registration.setNumRegistration(5L);

        assertEquals(5L, registration.getNumRegistration());
        assertEquals(20, registration.getNumWeek());
        assertNotNull(registration.getSkier());
        assertNotNull(registration.getCourse());
    }
}
