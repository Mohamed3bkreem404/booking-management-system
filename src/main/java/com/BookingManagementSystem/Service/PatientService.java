package com.BookingManagementSystem.Service;

import com.BookingManagementSystem.Model.Accounts;
import com.BookingManagementSystem.Model.DTO.PatientDTO;
import com.BookingManagementSystem.Model.Patient;
import com.BookingManagementSystem.Repository.AccountsRepo;
import com.BookingManagementSystem.Repository.PatientRepo;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AccountsRepo accountsRepo;

    @CachePut(value = "patients",key = "#result.id")
    public void addPatient(PatientDTO patientDTO) {
        Accounts acc = accountsRepo.findById(patientDTO.getAccount())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Patient patient = Patient.builder()
                .id(patientDTO.getId())
                .email(patientDTO.getEmail())
                .patientName(patientDTO.getPatientName())
                .phoneNo(patientDTO.getPhoneNo())
                .date(patientDTO.getDate())
                .account(acc)
                .build();
        Patient savedPatient = patientRepo.save(patient);
        PatientDTO.fromEntity(savedPatient);
    }

    @Cacheable(value = "patients" , key = "#id")
    public PatientDTO getPatient(Integer id) {
        Patient patient = patientRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        return PatientDTO.fromEntity(patient);
    }

    @Cacheable(value = "patients" , key = "'page_'+#page+'_'+#size+'_'+#sortBy")
    public Page<PatientDTO> getPagedPatients(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return patientRepo.findAll(pageable).map(PatientDTO::fromEntity);
    }

    @CachePut(value = "patients", key = "#id")
    public PatientDTO updatePatient(Integer id, PatientDTO patientDTO) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow( () -> new RuntimeException("Patient not found"));

        patient.setPatientName(patientDTO.getPatientName());
        patient.setDate(patientDTO.getDate());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhoneNo(patientDTO.getPhoneNo());
        Patient updated = patientRepo.save(patient);
        return PatientDTO.fromEntity(updated);
    }

    @CacheEvict(value = "patients" , key = "#id")
    public void deletePatient(Integer id) {
        if (!patientRepo.existsById(id)){
            throw new RuntimeException("Patient not found !");
        }
        patientRepo.deleteById(id);

    }
}
