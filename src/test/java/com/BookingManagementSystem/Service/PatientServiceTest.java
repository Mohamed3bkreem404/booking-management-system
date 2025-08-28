package com.BookingManagementSystem.Service;

import com.BookingManagementSystem.Model.Accounts;
import com.BookingManagementSystem.Model.DTO.PatientDTO;
import com.BookingManagementSystem.Model.Patient;
import com.BookingManagementSystem.Repository.AccountsRepo;
import com.BookingManagementSystem.Repository.PatientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class PatientServiceTest {

    @Mock
    private PatientRepo patientRepo;

    @Mock
    private AccountsRepo accountsRepo;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void addPatient() {
        Accounts acc = new Accounts();
        acc.setId(1);

        PatientDTO dto = new PatientDTO();
        dto.setId(1);
        dto.setPatientName("John Doe");
        dto.setEmail("john@example.com");
        dto.setPhoneNo("0123456789");
        dto.setDate(LocalDate.now());
        dto.setAccount(1);

        when(accountsRepo.findById(1)).thenReturn(Optional.of(acc));
        when(patientRepo.save(any(Patient.class))).thenAnswer(i -> i.getArguments()[0]);

        assertDoesNotThrow(() -> patientService.addPatient(dto));

        verify(patientRepo, times(1)).save(any(Patient.class));
    }

    private Properties verify(PatientRepo patientRepo, VerificationMode times) {
    }

}
