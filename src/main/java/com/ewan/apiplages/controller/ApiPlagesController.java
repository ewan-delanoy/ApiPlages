package com.ewan.apiplages.controller;


import com.ewan.apiplages.entity.Task;
import com.ewan.apiplages.input.ReservationInput;
import com.ewan.apiplages.request.CreateTaskInput;
import com.ewan.apiplages.service.ApiPlagesService;
import com.ewan.apiplages.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ApiPlagesController {

    public ApiPlagesService apiPlagesService;

    public ApiPlagesController(ApiPlagesService taskService) {
        this.apiPlagesService = apiPlagesService;
    }

    @PostMapping("/reservation")
    public ResponseEntity<Long> createReservation(@RequestBody ReservationInput reservationInput) {
        Long newReservationId = apiPlagesService.effectuerReservation(reservationInput);
        return new ResponseEntity<Long>(newReservationId, HttpStatus.CREATED);
    }
}
