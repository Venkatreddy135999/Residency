package com.venkat.residency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingConfirmation(String toEmail, String guestName, String bookingId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Booking Confirmation - Venkat Residency");
        message.setText("Dear " + guestName + ",\n\n" +
                       "Your booking has been confirmed!\n" +
                       "Booking ID: " + bookingId + "\n" +
                       "Thank you for choosing Venkat Residency.\n\n" +
                       "Best regards,\nVenkat Residency Team");
        
        mailSender.send(message);
    }
}