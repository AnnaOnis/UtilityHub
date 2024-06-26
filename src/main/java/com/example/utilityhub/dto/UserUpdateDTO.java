package com.example.utilityhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String address;

}
