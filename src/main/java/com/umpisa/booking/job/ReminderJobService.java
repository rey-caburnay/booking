package com.umpisa.booking.job;

import com.umpisa.booking.entity.Reservation;
import com.umpisa.booking.service.NotificationService;
import com.umpisa.booking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderJobService {

    private Logger logger = LoggerFactory.getLogger(ReminderJobService.class);

    private final ReservationService reservationService;
    private final NotificationService notificationService;

    @Scheduled(fixedRate = 3600000) // runs every hour
    public void sendReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fourHoursLater = now.plusHours(4);

        List<Reservation> upcomingReservations = reservationService.getReservationsBetween(now, fourHoursLater);
//        upcomingReservations.forEach(notificationService::sendReminderNotification);
        upcomingReservations.stream().forEach(reservation ->{
            logger.info("Send Reminder", reservation);
        });

    }
}
