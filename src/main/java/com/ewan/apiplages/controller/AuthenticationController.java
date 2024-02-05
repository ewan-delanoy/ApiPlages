package com.ewan.apiplages.controller;

import com.ewan.apiplages.entity.Client;
import com.ewan.apiplages.entity.Concessionnaire;
import com.ewan.apiplages.entity.Utilisateur;
import com.ewan.apiplages.input.ClientInput;
import com.ewan.apiplages.input.ConcessionnaireInput;
import com.ewan.apiplages.input.UtilisateurInput;
import com.ewan.apiplages.response.LoginResponse;
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
    public ResponseEntity<Client> signupCustomer(@RequestBody ClientInput clientInput) {
        Client registeredCustomer = authenticationService.signupCustomer(clientInput);
        return ResponseEntity.ok(registeredCustomer);
    }
    @PostMapping("/signup-manager")
    public ResponseEntity<Concessionnaire> registerManager(@RequestBody ConcessionnaireInput concessionnaireInput) {
        Concessionnaire registeredManager = authenticationService.signupManager(concessionnaireInput);
        return ResponseEntity.ok(registeredManager);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UtilisateurInput utilisateurInput) {
        Utilisateur authenticatedUtilisateur = authenticationService.authenticate(utilisateurInput);

        String jwtToken = jwtService.generateToken(authenticatedUtilisateur);

        LoginResponse loginResponse = new LoginResponse(jwtToken,jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
