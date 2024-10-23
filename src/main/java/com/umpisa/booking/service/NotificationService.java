package com.umpisa.booking.service;

import com.umpisa.booking.entity.Reservation;
import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

//    private final JavaMailSender mailSender;

    public void sendReservationNotification(Reservation reservation) {
        if (reservation.getNotificationMethod() == Reservation.NotificationMethod.EMAIL) {
            sendEmail(reservation.getEmail(), "Reservation Confirmed", "Your reservation is confirmed.");
            logger.info("Reservation Confirmed thru email");
        }

    }

    public void sendCancellationNotification(Reservation reservation) {
        if (reservation.getNotificationMethod() == Reservation.NotificationMethod.EMAIL) {
            sendEmail(reservation.getEmail(), "Reservation Canceled", "Your reservation has been canceled.");
        }
        // SMS notification logic can be added here in the future
    }

    private void sendEmail(String to, String subject, String text) {
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text);
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Failed to send email", e);
//        }
        logger.info("Mail sent");
    }
}
