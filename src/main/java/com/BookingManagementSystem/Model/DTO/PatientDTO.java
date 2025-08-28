package com.BookingManagementSystem.Model.DTO;

import com.BookingManagementSystem.Model.Accounts;
import com.BookingManagementSystem.Model.Booking;
import com.BookingManagementSystem.Model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Integer id;
    private String patientName;
    private String phoneNo;
    private String email;
    private LocalDate date;
    private int  account;

    public static PatientDTO fromEntity(Patient patient){
        return PatientDTO.builder()
                .id(patient.getId())
                .patientName(patient.getPatientName())
                .phoneNo(patient.getPhoneNo())
                .email(patient.getEmail())
                .date(patient.getDate())
                .account((int)patient.getAccount().getId())
                .build();
    }

    public static Patient fromDTO(PatientDTO patientDTO , Accounts accounts){
        return Patient.builder()
                .id(patientDTO.id)
                .patientName(patientDTO.patientName)
                .phoneNo(patientDTO.phoneNo)
                .email(patientDTO.email)
                .account(accounts)
                .date(patientDTO.date)
                .build();
    }

}
