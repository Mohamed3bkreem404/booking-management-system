package com.BookingManagementSystem.Service;

import com.BookingManagementSystem.Model.Accounts;
import com.BookingManagementSystem.Model.DTO.AccountsDTO;
import com.BookingManagementSystem.Repository.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

        @Autowired
        private  AccountsRepo accountsRepo;

        @Autowired
        private  PasswordEncoder passwordEncoder;



    public void registerUser(AccountsDTO accountsDTO){
            Accounts accounts = new Accounts();
                accounts.setId(accountsDTO.getId());
                accounts.setRole("USER");
                accounts.setEmail(accountsDTO.getEmail());
                accounts.setPassword(passwordEncoder.encode(accountsDTO.getPassword()));
                accounts.setUsername(accountsDTO.getUsername());
        accountsRepo.save(accounts);
    }



}
