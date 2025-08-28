package com.BookingManagementSystem.Repository;

import com.BookingManagementSystem.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Integer> {

        Optional<Accounts> findByUsername(String username);

}
