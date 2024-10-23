package com.umpisa.booking.controller;


import com.umpisa.booking.converter.ReservationConverter;
import com.umpisa.booking.dto.ReservationRequestDto;
import com.umpisa.booking.entity.Reservation;
import com.umpisa.booking.exception.ReservationCreateException;
import com.umpisa.booking.exception.ReservationNotFoundException;
import com.umpisa.booking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationRequestDto> createReservation(@RequestBody ReservationRequestDto reservation) throws ReservationCreateException {
         reservationService.createReservation(reservation);
        return new ResponseEntity(reservation, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationRequestDto> getReservation(@PathVariable Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationService.getReservationById(id).get();
        ReservationRequestDto dto = ReservationConverter.toDTO(reservation);


        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservationsByEmail(@RequestParam String email) {
        List<Reservation> reservations = reservationService.getAllReservationsByCustomer(email);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody ReservationRequestDto updatedReservation) throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationService.updateReservation(id, updatedReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
