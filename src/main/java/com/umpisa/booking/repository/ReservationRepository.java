package com.umpisa.booking.repository;


import com.umpisa.booking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByEmail(String email);
    List<Reservation> findByReservationDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}
