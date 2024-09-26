package com.warrioracademy.user.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "User firstname is required")
    private String firstName;

    @NotNull(message = "User firstname is required")
    private String lastName;

    @NotNull(message = "User firstname is required")
    @Email(message= "Customer email is no a valid email address")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "User username is required")
    @Column(unique = true)
    private String userName;

    @NotNull(message = "User dateOfBirth is required")
    private LocalDate dateOfBirth;

    // Numéro de téléphone avec validation du format selon le pays
    @Pattern(regexp = "\\+[0-9]{1,3}[0-9]{9,12}", message = "Invalid phone number format")
    private String phoneNumber;

    private String countryCode; // Code international

    // Relation One-to-Many avec Address
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> addresses = new HashSet<>();


    private String roles;
}
