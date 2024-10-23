package com.umpisa.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequestDto {

    private long id;
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDateTime reservationDateTime;
    private int numberOfGuests;
    private String notificationMethod;

}
