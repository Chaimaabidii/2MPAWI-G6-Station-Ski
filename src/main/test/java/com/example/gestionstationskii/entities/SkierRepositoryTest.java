package com.example.gestionstationskii.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SkierRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void testPersistSkier() {
        Skier skier = new Skier();
        skier.setFirstName("Mayssa");
        skier.setLastName("Arfaoui");
        skier.setDateOfBirth(LocalDate.of(2000, 5, 10));
        skier.setCity("Kef");

        entityManager.persist(skier);
        entityManager.flush();

        Skier found = entityManager.find(Skier.class, skier.getNumSkier());
        assertThat(found).isNotNull();
        assertThat(found.getFirstName()).isEqualTo("Mayssa");
    }
}
