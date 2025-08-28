package com.BookingManagementSystem.Model.DTO;

import com.BookingManagementSystem.Model.Accounts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountsDTO {

    private long id;
    private String username;
    private String email;
    private String password;
    private String role;

    public static AccountsDTO toAccountsDto(Accounts accounts){
        return AccountsDTO.builder()
                .id(accounts.getId())
                .username(accounts.getUsername())
                .email(accounts.getEmail())
                .role(accounts.getRole())
                .build();
    }
}
