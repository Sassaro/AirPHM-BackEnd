package org.example.controllers;

import org.example.DTO.ReservationDTO;
import org.example.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
class ReservationController
{

    @Autowired
    ReservationService reservationService;

    @PostMapping("/reservarHospedaje")
    void reserveLodging(@RequestBody ReservationDTO reservaDTO){
        this.reservationService.reserveLodging(reservaDTO);
    }

}