package com.umpisa.booking.service;

import com.umpisa.booking.dto.ReservationRequestDto;
import com.umpisa.booking.entity.Reservation;
import com.umpisa.booking.exception.ReservationCreateException;
import com.umpisa.booking.exception.ReservationNotFoundException;
import com.umpisa.booking.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, JavaMailSender mailSender) {
        this.reservationRepository = reservationRepository;
        this.mailSender = mailSender;
    }

    @Override
    public Reservation createReservation(ReservationRequestDto request) throws ReservationCreateException {
        // Logic to create a reservation
        try {
            Reservation reservation = new Reservation();
            // Set reservation details from the request DTO
            reservation.setName(request.getName());
            reservation.setEmail(request.getEmail());
            reservation.setPhoneNumber(request.getPhoneNumber());
            reservation.setReservationDateTime(request.getReservationDateTime());
            reservation.setNumberOfGuests(request.getNumberOfGuests());
            reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);

            // Save the reservation to the database
            reservation = reservationRepository.save(reservation);

            // Send confirmation email
            sendConfirmationEmail(reservation);

            return reservation;
        }catch (DataAccessException dae) {
            throw new ReservationCreateException();
        }
    }

    @Override
    public Reservation updateReservation(Long reservationId, ReservationRequestDto request)
            throws ReservationNotFoundException{
        // Logic to update a reservation
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        existingReservation.setReservationDateTime(request.getReservationDateTime());
        existingReservation.setNumberOfGuests(request.getNumberOfGuests());

        return reservationRepository.save(existingReservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        // Logic to cancel a reservation
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservationRepository.delete(reservation);

        // Send cancellation email
        sendCancellationEmail(reservation);
    }

    @Override
    public List<Reservation> getAllReservationsByCustomer(String customerEmail) {
        return reservationRepository.findByEmail(customerEmail);
    }

    @Override
    public Optional<Reservation> getReservationById(long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> getReservationsBetween(LocalDateTime start, LocalDateTime ends) {
        return reservationRepository.findByReservationDateTimeBetween(start, ends);
    }

    private void sendConfirmationEmail(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservation.getEmail());
        message.setSubject("Reservation Confirmation");
        message.setText("Your reservation has been confirmed!");
//        mailSender.send(message);
        logger.info("Send Confirmation", message);
    }

    private void sendCancellationEmail(Reservation reservation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservation.getEmail());
        message.setSubject("Reservation Cancellation");
        message.setText("Your reservation has been canceled.");
//        mailSender.send(message);
        logger.info("Send Cancellation", message);
    }
}

