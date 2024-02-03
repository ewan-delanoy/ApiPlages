package com.ewan.apiplages.controller;


import com.ewan.apiplages.input.FormInput;
import com.ewan.apiplages.input.ReservationInput;
import com.ewan.apiplages.output.TripleReservationOutput;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ApiPlagesController {

    public ApiPlagesService apiPlagesService;

    public ApiPlagesController(ApiPlagesService apiPlagesService) {
        this.apiPlagesService = apiPlagesService;
    }

    @PostMapping("/reservation")
    public ResponseEntity<Long> createReservation(@RequestBody ReservationInput reservationInput) {
        Long newReservationId = apiPlagesService.effectuerReservation(reservationInput);
        return new ResponseEntity<Long>(newReservationId, HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}/reservations")
    public @ResponseBody TripleReservationOutput reservationsClient(@PathVariable Long id) {
        return apiPlagesService.reservationsClient(id);
    }

    @GetMapping("/concessionnaires/{id}/reservations")
    public @ResponseBody TripleReservationOutput reservationsConcessionnaire(@PathVariable Long id) {
        return apiPlagesService.reservationsConcessionnaire(id);
    }

    @GetMapping("/temporary")
    public @ResponseBody FormInput temporary() {
        Long plageId = 1L;
        LocalDate dateDebut = LocalDate.of(2020, 6, 4);
        LocalDate dateFin = LocalDate.of(2020, 6, 19);
        return  new FormInput(plageId, dateDebut, dateFin);
    }



}
