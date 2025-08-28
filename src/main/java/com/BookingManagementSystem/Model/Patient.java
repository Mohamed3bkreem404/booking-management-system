package com.BookingManagementSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "patient_name")
    @NotBlank(message = "Patient name required")
    private String patientName;

    @Column(name = "phone_no")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits")
    private String phoneNo;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "birth_date")
    private LocalDate date;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id" , nullable = false)
   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   private Accounts account;
}
