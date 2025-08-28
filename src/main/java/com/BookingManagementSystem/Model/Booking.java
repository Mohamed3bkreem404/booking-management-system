package com.BookingManagementSystem.Model;


import com.BookingManagementSystem.Enum.BookingStatus;
import com.BookingManagementSystem.Model.DTO.BookingDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "check_in_date")
    @NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "Check-in date cannot be in the past")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    @NotNull(message = "Check-Out date is required")
    @Future(message = "Check-out date must be in the futur")
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    @NotNull(message = "Customer is required")
    private Patient customer;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @NotNull(message = "Doctor is required")
    private Accounts doctor;
}

