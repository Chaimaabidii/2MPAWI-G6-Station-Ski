package com.example.gestionstationskii.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gestionstationskii.entities.*;
import com.example.gestionstationskii.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SkierServicesImpl implements ISkierServices {

    private ISkierRepository skierRepository;
    private IPisteRepository pisteRepository;
    private ICourseRepository courseRepository;
    private IRegistrationRepository registrationRepository;
    private ISubscriptionRepository subscriptionRepository;

    @Override
    public List<Skier> retrieveAllSkiers() {
        System.out.println("📋 Récupération de la liste de tous les skieurs...");
        List<Skier> skiers = skierRepository.findAll();
        System.out.println("✅ Nombre de skieurs trouvés : " + skiers.size());
        return skiers;
    }

    @Override
    public Skier addSkier(Skier skier) {
        System.out.println("➕ Ajout d’un nouveau skieur : " + skier.getFirstName() + " " + skier.getLastName());
        switch (skier.getSubscription().getTypeSub()) {
            case ANNUAL:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusYears(1));
                break;
            case SEMESTRIEL:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusMonths(6));
                break;
            case MONTHLY:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusMonths(1));
                break;
        }
        System.out.println("📆 Abonnement défini jusqu’au : " + skier.getSubscription().getEndDate());
        return skierRepository.save(skier);
    }

    @Override
    public Skier assignSkierToSubscription(Long numSkier, Long numSubscription) {
        System.out.println("🔗 Assignation du skieur " + numSkier + " à l’abonnement " + numSubscription);
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        Subscription subscription = subscriptionRepository.findById(numSubscription).orElse(null);
        if (skier != null && subscription != null) {
            skier.setSubscription(subscription);
            System.out.println("✅ Assignation réussie !");
        } else {
            System.out.println("⚠️ Skieur ou abonnement introuvable !");
        }
        return skierRepository.save(skier);
    }

    @Override
    public Skier addSkierAndAssignToCourse(Skier skier, Long numCourse) {
        System.out.println("🎿 Ajout du skieur et assignation au cours ID = " + numCourse);
        Skier savedSkier = skierRepository.save(skier);
        Course course = courseRepository.getById(numCourse);
        Set<Registration> registrations = savedSkier.getRegistrations();
        for (Registration r : registrations) {
            r.setSkier(savedSkier);
            r.setCourse(course);
            registrationRepository.save(r);
            System.out.println("✅ Inscription ajoutée pour le cours " + course.getNumCourse());
        }
        return savedSkier;
    }

    @Override
    public void removeSkier(Long numSkier) {
        System.out.println("❌ Suppression du skieur ID = " + numSkier);
        skierRepository.deleteById(numSkier);
        System.out.println("✅ Skieur supprimé avec succès !");
    }

    @Override
    public Skier retrieveSkier(Long numSkier) {
        System.out.println("🔍 Recherche du skieur ID = " + numSkier);
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        if (skier != null)
            System.out.println("✅ Skieur trouvé : " + skier.getFirstName() + " " + skier.getLastName());
        else
            System.out.println("⚠️ Aucun skieur trouvé avec cet ID !");
        return skier;
    }

    @Override
    public Skier assignSkierToPiste(Long numSkieur, Long numPiste) {
        System.out.println("⛷️ Assignation du skieur " + numSkieur + " à la piste " + numPiste);
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Piste piste = pisteRepository.findById(numPiste).orElse(null);
        try {
            skier.getPistes().add(piste);
        } catch (NullPointerException exception) {
            Set<Piste> pisteList = new HashSet<>();
            pisteList.add(piste);
            skier.setPistes(pisteList);
        }
        System.out.println("✅ Piste assignée avec succès !");
        return skierRepository.save(skier);
    }

    @Override
    public List<Skier> retrieveSkiersBySubscriptionType(TypeSubscription typeSubscription) {
        System.out.println("📦 Recherche des skieurs avec un abonnement de type : " + typeSubscription);
        List<Skier> skiers = skierRepository.findBySubscription_TypeSub(typeSubscription);
        System.out.println("✅ Skieurs trouvés : " + skiers.size());
        return skiers;
    }
}
