package com.umpisa.booking.service;


import com.umpisa.booking.dto.ReservationRequestDto;
import com.umpisa.booking.entity.Reservation;
import com.umpisa.booking.exception.ReservationCreateException;
import com.umpisa.booking.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    public ReservationServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() throws ReservationCreateException {
        // Create and populate the DTO with valid data
        ReservationRequestDto dto = new ReservationRequestDto();
        dto.setName("John Doe");
        dto.setPhoneNumber("123-456-7890");
        dto.setEmail("john.doe@example.com");
        dto.setReservationDateTime(LocalDateTime.now());
        dto.setNumberOfGuests(4);

        // Create a mock Reservation object that would be saved
        Reservation reservation = new Reservation();
        reservation.setId(1L); // Set an ID if applicable
        reservation.setName(dto.getName());
        reservation.setPhoneNumber(dto.getPhoneNumber());
        reservation.setEmail(dto.getEmail());
        reservation.setReservationDateTime (dto.getReservationDateTime());
        reservation.setNumberOfGuests(dto.getNumberOfGuests());

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);


        // Call the service method to test
        Reservation createdReservation = reservationService.createReservation(dto);

        // Verify that the notification service is called with the correct reservation

        // Verify that the reservation was saved with the correct details
        verify(reservationRepository, times(1)).save(argThat(savedReservation ->
                savedReservation.getName().equals(dto.getName()) &&
                        savedReservation.getPhoneNumber().equals(dto.getPhoneNumber()) &&
                        savedReservation.getEmail().equals(dto.getEmail()) &&
                        savedReservation.getReservationDateTime().equals(dto.getReservationDateTime()) &&
                        savedReservation.getNumberOfGuests() == (dto.getNumberOfGuests()) &&
                        savedReservation.getStatus() == Reservation.ReservationStatus.CONFIRMED
        ));



    }

}
