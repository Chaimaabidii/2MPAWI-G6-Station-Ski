package com.example.gestionstationskii.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

class SkierTest {

    @Test
    void testSkierCreationAndAttributes() {
        Skier skier = new Skier();
        skier.setFirstName("Mayssa");
        skier.setLastName("Arfaoui");
        skier.setDateOfBirth(LocalDate.of(2000, 5, 10));
        skier.setCity("Kef");

        assertEquals("Mayssa", skier.getFirstName());
        assertEquals("Arfaoui", skier.getLastName());
        assertEquals(LocalDate.of(2000, 5, 10), skier.getDateOfBirth());
        assertEquals("Kef", skier.getCity());

        Subscription sub = new Subscription();
        sub.setNumSub(1L);
        skier.setSubscription(sub);
        assertEquals(sub, skier.getSubscription());

        skier.setPistes(new HashSet<>());
        assertNotNull(skier.getPistes());

        skier.setRegistrations(new HashSet<>());
        assertTrue(skier.getRegistrations().isEmpty());
    }
}
