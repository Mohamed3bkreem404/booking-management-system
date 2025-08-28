package com.BookingManagementSystem.Repository;

import com.BookingManagementSystem.Enum.BookingStatus;
import com.BookingManagementSystem.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
        List<Booking> findByBookingStatus(BookingStatus status);
}
