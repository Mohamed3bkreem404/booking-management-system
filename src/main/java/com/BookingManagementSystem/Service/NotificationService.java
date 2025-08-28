package com.BookingManagementSystem.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendBookingConfirmationEmail(String email){
        try {
            Thread.sleep(5000);
            System.out.println("Email sent to "+ email);
        }catch (InterruptedException e){
            throw new IllegalStateException(e);
        }
    }
}
