package com.warrioracademy.user.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private LocalDate dateOfBirth;
    private String roles;
    private String phoneNumber;
    private String countryCode;
}
