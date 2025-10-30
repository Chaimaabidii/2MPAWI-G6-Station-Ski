package com.example.gestionstationskii;

import com.example.gestionstationskii.entities.Registration;
import com.example.gestionstationskii.entities.Skier;
import com.example.gestionstationskii.entities.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationTest {

    private Registration registration;
    private Skier mockSkier;
    private Course mockCourse;

    @BeforeEach
    void setUp() {
        mockSkier = Mockito.mock(Skier.class);
        mockCourse = Mockito.mock(Course.class);
        registration = new Registration(1L, 10, mockSkier, mockCourse);
    }

    // ✅ 1️⃣ Test de création et affectation des champs simples
    @Test
    void testBasicFieldsAssignment() {
        registration.setNumWeek(15);
        registration.setNumRegistration(2L);

        assertEquals(2L, registration.getNumRegistration());
        assertEquals(15, registration.getNumWeek());
    }

    // ✅ 2️⃣ Test de relations ManyToOne (Skier et Course)
    @Test
    void testRelationships() {
        assertEquals(mockSkier, registration.getSkier());
        assertEquals(mockCourse, registration.getCourse());

        // On change les relations
        Skier newSkier = Mockito.mock(Skier.class);
        Course newCourse = Mockito.mock(Course.class);
        registration.setSkier(newSkier);
        registration.setCourse(newCourse);

        assertEquals(newSkier, registration.getSkier());
        assertEquals(newCourse, registration.getCourse());
    }

    // ✅ 3️⃣ Test du constructeur complet et du toString()
    @Test
    void testAllArgsConstructorAndToString() {
        Registration reg2 = new Registration(3L, 7, mockSkier, mockCourse);

        assertEquals(3L, reg2.getNumRegistration());
        assertEquals(7, reg2.getNumWeek());
        assertTrue(reg2.toString().contains("numWeek=7"));
    }

    // ✅ 4️⃣ Test de comportement avec Mockito (interactions)
    @Test
    void testMockitoInteractions() {
        when(mockSkier.toString()).thenReturn("FakeSkier");
        when(mockCourse.toString()).thenReturn("FakeCourse");

        String str = registration.toString();

        assertTrue(str.contains("FakeSkier"));
        assertTrue(str.contains("FakeCourse"));

        // ❌ Supprimé : ne pas vérifier toString() sur les mocks
        // verify(mockSkier, times(1)).toString();
        // verify(mockCourse, times(1)).toString();
    }
}
