package com.warrioracademy.user.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @NotNull(message = "Street is required")
    private String street;

    @NotNull(message = "House number is required")
    private String houseNumber;

    @NotNull(message = "Zip code is required")
    @Pattern(regexp = "\\d{5}", message = "Invalid zip code")
    private String zipCode;

    @NotNull(message = "City is required")
    private String city;
}