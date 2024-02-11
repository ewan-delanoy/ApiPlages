package com.ewan.apiplages.service;

import com.ewan.apiplages.dao.ClientDao;
import com.ewan.apiplages.dao.ConcessionnaireDao;
import com.ewan.apiplages.dao.UtilisateurDao;
import com.ewan.apiplages.entity.Client;
import com.ewan.apiplages.entity.Concessionnaire;
import com.ewan.apiplages.entity.Utilisateur;
import com.ewan.apiplages.input.ClientRegistrationInput;
import com.ewan.apiplages.input.ConcessionnaireInput;
import com.ewan.apiplages.input.LoginInput;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UtilisateurDao utilisateurDao;

    private final ClientDao clientDao;

    private final ConcessionnaireDao concessionnaireDao;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UtilisateurDao utilisateurDao,
            ClientDao clientDao,
            ConcessionnaireDao concessionnaireDao,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.utilisateurDao = utilisateurDao;
        this.clientDao = clientDao ;
        this.concessionnaireDao = concessionnaireDao;
        this.passwordEncoder = passwordEncoder;
    }

    public Client signupCustomer(ClientRegistrationInput input) {
        Client client = new Client(input,passwordEncoder);
        return clientDao.save(client);
    }

    public Concessionnaire signupManager(ConcessionnaireInput input) {
        Concessionnaire concessionnaire = new  Concessionnaire(input,passwordEncoder);
        return concessionnaireDao.save(concessionnaire);
    }

    public Utilisateur authenticate(LoginInput input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.motDePasse()
                )
        );

        return utilisateurDao.findByEmail(input.email())
                .orElseThrow();
    }
}

