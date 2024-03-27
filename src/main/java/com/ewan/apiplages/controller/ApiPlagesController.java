package com.ewan.apiplages.controller;


import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.input.*;
import com.ewan.apiplages.output.*;
import com.ewan.apiplages.service.ApiPlagesService;
import com.ewan.apiplages.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ApiPlagesController {

    public ApiPlagesService apiPlagesService;

    public ApiPlagesController(ApiPlagesService apiPlagesService) {
        this.apiPlagesService = apiPlagesService;
    }

    @PostMapping("/clients/reservation")
    public ResponseEntity<Long> effectuerReservation(@RequestBody ReservationInput reservationInput) {
        Long newReservationId = apiPlagesService.effectuerReservation(reservationInput);
        return new ResponseEntity<>(newReservationId, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}/reservations")
    public @ResponseBody ResponseEntity<TripleReservationOutput> reservationsClient(@PathVariable Long id) {
        return new ResponseEntity<>(apiPlagesService.reservationsClient(id), HttpStatus.OK);
    }

    @DeleteMapping("/clients/reservations/{rid}")
    public @ResponseBody ResponseEntity<Boolean> supprimerReservation(@PathVariable Long rid) {
        apiPlagesService.supprimerReservation(rid);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/clients/form-data")
    public @ResponseBody ResponseEntity<PreparationReservationOutput> preparerFormulaire(@RequestBody PreparationReservationInput preparationReservationInput) {
        return new ResponseEntity<>(apiPlagesService.preparerFormulaire(preparationReservationInput), HttpStatus.OK);
    }
    @GetMapping("/concessionnaires/{id}/reservations")
    public @ResponseBody ResponseEntity<TripleReservationOutput> reservationsConcessionnaire(@PathVariable Long id) {
        return new ResponseEntity<>(apiPlagesService.reservationsConcessionnaire(id), HttpStatus.OK);
    }

    @PostMapping("/concessionnaires/{cid}/reservations/{rid}")
    public ResponseEntity<Boolean> editerStatutReservation(@RequestBody String statutNom,@PathVariable Long rid) {
        apiPlagesService.editerStatutReservation(rid,statutNom);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @PostMapping("/clients")
    public ResponseEntity<Long> inscrireNouveauClient(@RequestBody ClientRegistrationInput clientRegistrationInput) {
        Long newClientId = apiPlagesService.inscrireNouveauClient(clientRegistrationInput);
        return new ResponseEntity<>(newClientId, HttpStatus.OK);
    }

    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<UtilisateurOutput> informationsUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(apiPlagesService.getUtilisateurById(id));
    }


    @PostMapping("/connexion")
    public @ResponseBody ResponseEntity<LoginOutput> connecterUtilisateur(@RequestBody LoginInput loginInput) {
        LoginOutput resultat = apiPlagesService.connecterUtilisateur(loginInput);
          return new ResponseEntity<>(resultat, HttpStatus.OK);
    }

    @GetMapping("/plages")
    public @ResponseBody ResponseEntity<List<PlageOutput>> getPlages() {
        return new ResponseEntity<>(apiPlagesService.getPlages(), HttpStatus.OK);
    }

    @GetMapping("/pays")
    public @ResponseBody ResponseEntity<List<PaysOutput>> getPays() {
        return new ResponseEntity<>(apiPlagesService.getPays(), HttpStatus.OK);
    }

    @GetMapping("/ask-for-form-input")
    public @ResponseBody PreparationReservationInput askForFormInput() {
        Long plageId = 1L;
        LocalDate dateDebut = LocalDate.of(2020, 6, 4);
        LocalDate dateFin = LocalDate.of(2020, 6, 19);
        return  new PreparationReservationInput(plageId, dateDebut, dateFin);
    }

    @GetMapping("/ask-for-client-input")
    public @ResponseBody ClientRegistrationInput askForClientInput() {
        // LocalDateTime dateHeureInscription = LocalDateTime.of(2019,
        //        Month.JULY, 29, 19, 30, 40);
        return  new ClientRegistrationInput("Blair", "Anthony", "tony.blair@mail.co.uk",
                "yeah its me",
                new PaysInput("GB","Grande-Bretagne"));
    }


    @GetMapping("/ask-for-reservation-input")
    public @ResponseBody ReservationInput askForMReservationInput() {
        Long clientId = 5L;
        Long plageId = 1L;
        List<AffectationInput> affectations= Arrays.asList(
                new AffectationInput((byte) 1, (byte) 2,(byte)1,(byte)0),
                new AffectationInput((byte) 1, (byte) 2,(byte)0,(byte)1)
        );
        LocalDate dateDebut = LocalDate.of(2019, 7, 3);
        LocalDate dateFin = LocalDate.of(2019, 7, 8);
        String dateDebutString = dateDebut.format(Util.formatter);
        String dateFinString = dateFin.format(Util.formatter);
        String aucunLien = LienDeParenteEnum.AUCUN_LIEN.getNom();
        return new ReservationInput(clientId,plageId,affectations,dateDebutString,dateFinString,aucunLien,"0000000000000000", (byte) 1, (short) 2027, "321");
    }

}
