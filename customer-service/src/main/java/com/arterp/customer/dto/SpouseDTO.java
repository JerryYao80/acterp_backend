package com.arterp.customer.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

@Data
public class SpouseDTO {
    private Long id;
    private Long customerId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "ID type is required")
    private String idType;

    @NotBlank(message = "ID number is required")
    private String idNumber;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    private String medicalHistory;
    private String familyHistory;
    private String geneticScreening;

    private Set<String> documentUrls;
    private String notes;

    @NotNull(message = "Insurance status is required")
    private Boolean hasInsurance;

    private String insuranceInformation;
    private String emergencyContact;

    @NotBlank(message = "Preferred language is required")
    private String preferredLanguage;

    @NotBlank(message = "Communication preference is required")
    private String communicationPreference;
} 