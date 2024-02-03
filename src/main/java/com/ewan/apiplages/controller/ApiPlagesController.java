package com.ewan.apiplages.controller;


import com.ewan.apiplages.input.ClientInput;
import com.ewan.apiplages.input.ConcessionnaireInput;
import com.ewan.apiplages.input.FormInput;
import com.ewan.apiplages.input.ReservationInput;
import com.ewan.apiplages.output.TripleReservationOutput;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ApiPlagesController {

    public ApiPlagesService apiPlagesService;

    public ApiPlagesController(ApiPlagesService apiPlagesService) {
        this.apiPlagesService = apiPlagesService;
    }

    @PostMapping("/reservation")
    public ResponseEntity<Long> createReservation(@RequestBody ReservationInput reservationInput) {
        Long newReservationId = apiPlagesService.effectuerReservation(reservationInput);
        return new ResponseEntity<>(newReservationId, HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}/reservations")
    public @ResponseBody TripleReservationOutput reservationsClient(@PathVariable Long id) {
        return apiPlagesService.reservationsClient(id);
    }

    @GetMapping("/concessionnaires/{id}/reservations")
    public @ResponseBody TripleReservationOutput reservationsConcessionnaire(@PathVariable Long id) {
        return apiPlagesService.reservationsConcessionnaire(id);
    }

    @PostMapping("/clients")
    public ResponseEntity<Long> signUpNewCustomer(@RequestBody ClientInput clientInput) {
        Long newClientId = apiPlagesService.inscrireNouveauClient(clientInput);
        return new ResponseEntity<>(newClientId, HttpStatus.CREATED);
    }

    @PostMapping("/clients")
    public ResponseEntity<Long> signUpNewManager(@RequestBody ConcessionnaireInput concessionnaireInput) {
        Long newConcessionnaireId = apiPlagesService.inscrireNouveauConcessionnaire(concessionnaireInput);
        return new ResponseEntity<>(newConcessionnaireId, HttpStatus.CREATED);
    }
    @GetMapping("/temporary")
    public @ResponseBody FormInput temporary() {
        Long plageId = 1L;
        LocalDate dateDebut = LocalDate.of(2020, 6, 4);
        LocalDate dateFin = LocalDate.of(2020, 6, 19);
        return  new FormInput(plageId, dateDebut, dateFin);
    }



}
