package com.BookingManagementSystem.Controller;

import com.BookingManagementSystem.Model.DTO.AccountsDTO;
import com.BookingManagementSystem.Service.AccountsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
public class AccountsController {

        @Autowired
        private  AccountsService accountsService;
        @Autowired
        private  MessageSource messageSource;


        @PostMapping("/register")
            public ResponseEntity<String> register(
                    @Valid @RequestBody AccountsDTO accountsDTO , Locale locale){
                    accountsService.registerUser(accountsDTO);
                    String message = messageSource.getMessage("account.added",null,locale);
                    return ResponseEntity.ok (message);
            }

    


}
