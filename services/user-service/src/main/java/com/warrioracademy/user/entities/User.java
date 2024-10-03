package com.warrioracademy.user.entities;


import com.warrioracademy.user.config.AuditorAwareProvider;
import com.warrioracademy.user.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated

public class User extends AuditorAwareProvider implements Serializable {
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

    @Pattern(regexp = "\\+[0-9]{1,3}", message = "Invalid international code")
    private String countryCode; // Code international

    // Numéro de téléphone avec validation du format selon le pays
    @Pattern(regexp = "[0-9]{8,12}", message = "Invalid phone number format")
    private String phoneNumber;

    // Relation One-to-Many avec Address
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> addresses = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role roles;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime latModified;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;
    @Column(insertable = false)
    @LastModifiedDate
    private String lastModifiedBy;
}
