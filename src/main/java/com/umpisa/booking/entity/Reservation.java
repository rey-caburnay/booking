package com.umpisa.booking.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;
    private LocalDateTime reservationDateTime;
    private int numberOfGuests;

    @Enumerated(EnumType.STRING)
    private NotificationMethod notificationMethod;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public enum NotificationMethod {
        EMAIL, SMS
    }

    public enum ReservationStatus {
        CONFIRMED, CANCELED
    }
}
