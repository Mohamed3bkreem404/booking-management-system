package com.BookingManagementSystem.Model;

import com.BookingManagementSystem.Model.DTO.AccountsDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{username.required}")
    @Size(min = 4 , max = 20 , message = "Username must be between 4 and 20 characters")
    @Column(name = "username" , nullable = false , unique = true ,  length = 20)
    private String username;

    @Email(message = "{email.invalid}")
    @NotBlank(message = "{email.required}")
    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @NotBlank(message = "{password.required}")
    @Size(min =4 , max = 60 , message = "Password must be between 4 and 60 characters")
    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    private String role;

    public static Accounts toDataBase(AccountsDTO accountsDTO, PasswordEncoder passwordEncoder){
        return Accounts.builder()
                .id(accountsDTO.getId())
                .username(accountsDTO.getUsername())
                .password(passwordEncoder.encode(accountsDTO.getPassword()))
                .email(accountsDTO.getEmail())
                .role(accountsDTO.getRole())
                .build();
    }

    @OneToMany(
            mappedBy = "account",
            cascade = { CascadeType.PERSIST , CascadeType.MERGE } ,
            orphanRemoval = false
    )
    @Builder.Default
    @ToString.Exclude
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient p){
        patients.add(p);
        p.setAccount(this);
    }

    @OneToMany(mappedBy = "doctor")
    private List<Booking> bookings = new ArrayList<>();


    public void removePatient(Patient p){
        patients.remove(p);
        p.setAccount(null);
    }


}
