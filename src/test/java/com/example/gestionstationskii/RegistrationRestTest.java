package com.example.gestionstationskii;
import com.example.gestionstationskii.entities.Registration;
import com.example.gestionstationskii.entities.Skier;
import com.example.gestionstationskii.entities.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class RegistrationRestTest {

        @Test
        public void testRegistrationGettersAndSetters() {
            // Création des objets liés
            Skier skier = new Skier();
            Course course = new Course();

            // Création d'une instance de Registration
            Registration registration = new Registration();
            registration.setNumWeek(42);
            registration.setSkier(skier);
            registration.setCourse(course);

            // Vérification des valeurs
            Assertions.assertEquals(42, registration.getNumWeek());
            Assertions.assertEquals(skier, registration.getSkier());
            Assertions.assertEquals(course, registration.getCourse());

            // Test du constructeur complet
            Registration reg2 = new Registration(1L, 7, skier, course);
            Assertions.assertEquals(1L, reg2.getNumRegistration());
            Assertions.assertEquals(7, reg2.getNumWeek());
            Assertions.assertEquals(skier, reg2.getSkier());
            Assertions.assertEquals(course, reg2.getCourse());
        }
    }


