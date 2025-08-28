package com.BookingManagementSystem.Service;

import com.BookingManagementSystem.Model.Accounts;
import com.BookingManagementSystem.Repository.AccountsRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountsRepo accountsRepo;

    public AccountDetailsService(AccountsRepo accountsRepo) {
        this.accountsRepo = accountsRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Search username in the database
        Accounts accounts = accountsRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new User(
                accounts.getUsername(),
                accounts.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + accounts.getRole()))
        );
    }
}
