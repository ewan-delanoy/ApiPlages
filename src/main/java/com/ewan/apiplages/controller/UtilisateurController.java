package com.ewan.apiplages.controller;

import com.ewan.apiplages.entity.Utilisateur;
import com.ewan.apiplages.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/utilisateurs")
@RestController
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/me")
    public ResponseEntity<Utilisateur> authenticatedUtilisateur() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Utilisateur currentUtilisateur = (Utilisateur) authentication.getPrincipal();

        return ResponseEntity.ok(currentUtilisateur);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> allUtilisateurs() {
        List <Utilisateur> utilisateurs = utilisateurService.allUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }
}

