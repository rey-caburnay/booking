package com.umpisa.booking.service;

import com.umpisa.booking.dto.ReservationRequestDto;
import com.umpisa.booking.entity.Reservation;
import com.umpisa.booking.exception.ReservationCreateException;
import com.umpisa.booking.exception.ReservationNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(ReservationRequestDto request) throws ReservationCreateException;
    Reservation updateReservation(Long reservationId, ReservationRequestDto request) throws ReservationNotFoundException;
    void cancelReservation(Long reservationId);
    List<Reservation> getAllReservationsByCustomer(String customerEmail);

    Optional<Reservation> getReservationById(long id);
    List<Reservation> getReservationsBetween(LocalDateTime start, LocalDateTime ends);

}

