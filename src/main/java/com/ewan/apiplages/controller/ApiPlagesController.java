package com.ewan.apiplages.controller;


import com.ewan.apiplages.enumeration.LienDeParenteEnum;
import com.ewan.apiplages.input.*;
import com.ewan.apiplages.output.PreparationFormulaireOutput;
import com.ewan.apiplages.output.TripleReservationOutput;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Controller
public class ApiPlagesController {

    public ApiPlagesService apiPlagesService;

    public ApiPlagesController(ApiPlagesService apiPlagesService) {
        this.apiPlagesService = apiPlagesService;
    }

    @PostMapping("/clients/{id}/reservation")
    public ResponseEntity<Long> createReservation(@RequestBody ReservationInput reservationInput) {
        Long newReservationId = apiPlagesService.effectuerReservation(reservationInput);
        return new ResponseEntity<>(newReservationId, HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}/reservations")
    public @ResponseBody ResponseEntity<TripleReservationOutput> reservationsClient(@PathVariable Long id) {
        return new ResponseEntity<>(apiPlagesService.reservationsClient(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/clients/{id}/reservations/{rid}")
    public @ResponseBody ResponseEntity<Boolean> supprimerReservation(@PathVariable Long rid) {
        apiPlagesService.supprimerReservation(rid);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/clients/{id}/form-data")
    public @ResponseBody ResponseEntity<PreparationFormulaireOutput> preparerFormulaire(@RequestBody FormInput formInput) {
        return new ResponseEntity<>(apiPlagesService.preparerFormulaire(formInput), HttpStatus.FOUND);
    }
    @GetMapping("/concessionnaires/{id}/reservations")
    public @ResponseBody ResponseEntity<TripleReservationOutput> reservationsConcessionnaire(@PathVariable Long id) {
        return new ResponseEntity<>(apiPlagesService.reservationsConcessionnaire(id), HttpStatus.FOUND);
    }

    @PostMapping("/concessionnaires/{cid}/reservations/{rid}")
    public ResponseEntity<Boolean> editerStatutReservation(@RequestBody String statutNom,@PathVariable Long rid) {
        apiPlagesService.editerStatutReservation(rid,statutNom);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
    @PostMapping("/clients")
    public ResponseEntity<Long> signUpNewCustomer(@RequestBody ClientInput clientInput) {
        Long newClientId = apiPlagesService.inscrireNouveauClient(clientInput);
        return new ResponseEntity<>(newClientId, HttpStatus.CREATED);
    }

    @PostMapping("/concessionnaires")
    public ResponseEntity<Long> signUpNewManager(@RequestBody ConcessionnaireInput concessionnaireInput) {
        Long newConcessionnaireId = apiPlagesService.inscrireNouveauConcessionnaire(concessionnaireInput);
        return new ResponseEntity<>(newConcessionnaireId, HttpStatus.CREATED);
    }
    @GetMapping("/ask-for-form-input")
    public @ResponseBody FormInput askForFormInput() {
        Long plageId = 1L;
        LocalDate dateDebut = LocalDate.of(2020, 6, 4);
        LocalDate dateFin = LocalDate.of(2020, 6, 19);
        return  new FormInput(plageId, dateDebut, dateFin);
    }

    @GetMapping("/ask-for-client-input")
    public @ResponseBody ClientInput askForClientInput() {
        LocalDateTime dateHeureInscription = LocalDateTime.of(2019,
                Month.JULY, 29, 19, 30, 40);
        return  new ClientInput("Blair", "Anthony", "tony.blair@mail.co.uk",
                "yeah its me",
                new PaysInput("GB","Grande-Bretagne"), dateHeureInscription);
    }

    @GetMapping("/ask-for-manager-input")
    public @ResponseBody ConcessionnaireInput askForManagerInput() {
        return  new ConcessionnaireInput("Vega", "Suzanne",
                "suzanne.vega@yahoo.us", "bookOfDreams",
                "0674231569");
    }

    @GetMapping("/ask-for-reservation-input")
    public @ResponseBody ReservationInput askForMReservationInput() {
        Long clientId = 5L;
        Long plageId = 1L;
        List<AffectationInput> affectations= List.of(
                new AffectationInput(60L,(byte)1,(byte)0),
                new AffectationInput(120L,(byte)0,(byte)1)
        );
        LocalDate dateDebut = LocalDate.of(2019, 7, 3);
        LocalDate dateFin = LocalDate.of(2019, 7, 8);
        String aucunLien = LienDeParenteEnum.AUCUN_LIEN.getNom();
        return new ReservationInput(clientId,plageId,affectations,dateDebut,dateFin,aucunLien);
    }

}
