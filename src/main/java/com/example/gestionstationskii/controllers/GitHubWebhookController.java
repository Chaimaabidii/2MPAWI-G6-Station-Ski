package com.example.gestionstationskii.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github-webhook")
public class GitHubWebhookController {

    @PostMapping
    public String handleWebhook(@RequestBody String payload) {
        System.out.println("📬 Webhook received from GitHub:");
        System.out.println(payload); // Affiche le JSON du push
        return "Webhook received!";
    }
}