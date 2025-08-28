package com.BookingManagementSystem.Model.DTO;

import com.BookingManagementSystem.Enum.BookingStatus;
import com.BookingManagementSystem.Model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {

    private Integer id;
    private Integer customerId;   // Patient.id
    private Long doctorId;        // Accounts.id (long في Accounts)
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;

    public static BookingDTO fromDTO(Booking booking){
        return BookingDTO.builder()
                .id(booking.getId())
                .customerId(booking.getCustomer() != null ? booking.getCustomer().getId() : null)
                .doctorId(booking.getDoctor() != null ? booking.getDoctor().getId() : null)
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .bookingStatus(booking.getBookingStatus())
                .build();
    }

    public static Booking toEntity(BookingDTO dto){
        return Booking.builder()
                .id(dto.getId())
                .checkInDate(dto.getCheckInDate())
                .checkOutDate(dto.getCheckOutDate())
                .bookingStatus(dto.getBookingStatus() != null ? dto.getBookingStatus() : BookingStatus.PENDING)
                .build();
    }
}
