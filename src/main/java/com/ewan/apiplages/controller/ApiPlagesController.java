package com.ewan.apiplages.controller;



import com.ewan.apiplages.enumeration.StatutEnum;
import com.ewan.apiplages.input.ReservationInput;
import com.ewan.apiplages.output.ReservationOutput;
import com.ewan.apiplages.service.ApiPlagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/reservations/{id}")
    public @ResponseBody List<ReservationOutput> reservationsClient(@PathVariable Long id) {
        String acceptee = StatutEnum.ACCEPTEE.getNom();
        List<ReservationOutput> reservations = apiPlagesService.reservationsClient(id, acceptee);
        return reservations;

    }

}
