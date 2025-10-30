package com.example.gestionstationskii;

import com.example.gestionstationskii.entities.Registration;
import com.example.gestionstationskii.entities.Skier;
import com.example.gestionstationskii.entities.Course;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class RegistrationMockitoTest {

    @Mock
    private Skier mockSkier;

    @Mock
    private Course mockCourse;

    @InjectMocks
    private Registration registration; // Injecte les mocks dans l’objet à tester

    // ✅ 1️⃣ Test de création de l’objet et des champs simples
    @Test
    @Order(1)
    void testCreateRegistration() {
        registration.setNumRegistration(1L);
        registration.setNumWeek(10);

        log.info("Création d'une registration avec numRegistration={} et numWeek={}",
                registration.getNumRegistration(), registration.getNumWeek());

        Assertions.assertEquals(1L, registration.getNumRegistration());
        Assertions.assertEquals(10, registration.getNumWeek());
    }

    // ✅ 2️⃣ Test des relations ManyToOne (Skier et Course)
    @Test
    @Order(2)
    void testRelations() {
        registration.setSkier(mockSkier);
        registration.setCourse(mockCourse);

        log.info("Association de Skier et Course à la registration");

        Assertions.assertEquals(mockSkier, registration.getSkier());
        Assertions.assertEquals(mockCourse, registration.getCourse());
    }

    // ✅ 3️⃣ Test de modification des champs
    @Test
    @Order(3)
    void testModifyFields() {
        registration.setNumRegistration(2L);
        registration.setNumWeek(15);

        log.info("Modification des champs : numRegistration={}, numWeek={}",
                registration.getNumRegistration(), registration.getNumWeek());

        Assertions.assertEquals(2L, registration.getNumRegistration());
        Assertions.assertEquals(15, registration.getNumWeek());
    }

    // ✅ 4️⃣ Test de comportement avec Mockito (vérification des interactions sur les mocks)
    @Test
    @Order(4)
    void testMockitoInteractions() {
        when(mockSkier.toString()).thenReturn("FakeSkier");
        when(mockCourse.toString()).thenReturn("FakeCourse");

        String str = registration.toString();

        log.info("Vérification du toString de Registration : {}", str);

        Assertions.assertTrue(str.contains("FakeSkier"));
        Assertions.assertTrue(str.contains("FakeCourse"));

        // ❌ On ne peut pas verify() toString() sur un mock final
        // Vérification indirecte possible via getters
        registration.setSkier(mockSkier);
        registration.setCourse(mockCourse);

        Assertions.assertEquals(mockSkier, registration.getSkier());
        Assertions.assertEquals(mockCourse, registration.getCourse());

        // Si on voulait vérifier des méthodes spécifiques de Skier ou Course, on ferait verify() ici
    }
}
