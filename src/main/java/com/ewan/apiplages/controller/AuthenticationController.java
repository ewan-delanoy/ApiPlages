package com.ewan.apiplages.controller;

import com.ewan.apiplages.entity.Client;
import com.ewan.apiplages.entity.Utilisateur;
import com.ewan.apiplages.input.ClientRegistrationInput;
import com.ewan.apiplages.input.LoginInput;
import com.ewan.apiplages.output.LoginOutput;
import com.ewan.apiplages.service.AuthenticationService;
import com.ewan.apiplages.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup-customer")
    public ResponseEntity<Client> signupCustomer(@RequestBody ClientRegistrationInput clientRegistrationInput) {
        Client registeredCustomer = authenticationService.signupCustomer(clientRegistrationInput);
        return ResponseEntity.ok(registeredCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> authenticate(@RequestBody LoginInput loginInput) {
        Utilisateur authenticatedUtilisateur = authenticationService.authenticate(loginInput);

        String jwtToken = jwtService.generateToken(authenticatedUtilisateur);

        LoginOutput loginResponse = new LoginOutput(jwtToken,jwtService.getExpirationTime(),
                 authenticatedUtilisateur.getUtilisateurId());

        return ResponseEntity.ok(loginResponse);
    }
}
