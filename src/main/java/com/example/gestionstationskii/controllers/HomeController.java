package com.example.gestionstationskii.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bienvenue à la station Ski !";
    }

    // Optionnel : mapping exact pour le contexte
    @GetMapping("/stationSkii")
    public String station() {
        return "Page principale de Station Ski";
    }
}
