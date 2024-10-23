package com.umpisa.booking.converter;

import com.umpisa.booking.dto.ReservationRequestDto;
import com.umpisa.booking.entity.Reservation;

public class ReservationConverter {


    public static ReservationRequestDto toDTO(Reservation entity) {
        ReservationRequestDto dto = new ReservationRequestDto();
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setReservationDateTime(entity.getReservationDateTime());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setNumberOfGuests(entity.getNumberOfGuests());
        dto.setId(entity.getId());
        return dto;
    }
}
