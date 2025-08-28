package com.BookingManagementSystem.Controller;

import com.BookingManagementSystem.Model.DTO.PatientDTO;
import com.BookingManagementSystem.Model.Patient;
import com.BookingManagementSystem.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Integer id){
        return ResponseEntity.ok(patientService.getPatient(id));
    }

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> getPagedPatients(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "id")String sortBy){
        return ResponseEntity.ok(patientService.getPagedPatients(page , size , sortBy));
    }

    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody @Valid PatientDTO patientDTO , Locale locale){
        String message = messageSource.getMessage("patient.added",null,locale);
        patientService.addPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(
            @Valid
            @PathVariable Integer id,
            @RequestBody PatientDTO patientDTO) {
        PatientDTO updatedPatient = patientService.updatePatient(id,patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
    }



}
