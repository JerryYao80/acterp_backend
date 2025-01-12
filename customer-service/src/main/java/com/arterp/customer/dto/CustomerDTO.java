package com.arterp.customer.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;

    private LocalDate birthDate;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "ID type is required")
    private String idType;

    @NotBlank(message = "ID number is required")
    private String idNumber;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    private String maritalStatus;
    private String medicalHistory;
    private String familyHistory;
    private String geneticScreening;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Customer type is required")
    private String customerType;

    private String requirements;
    private String preferences;

    @NotBlank(message = "Risk level is required")
    private String riskLevel;

    private List<String> addresses = new ArrayList<>();
    private List<String> documentUrls = new ArrayList<>();
    private String notes;

    @NotNull(message = "Insurance status is required")
    private Boolean hasInsurance;

    private String insuranceInformation;
    private String source;
    private String emergencyContact;

    @NotBlank(message = "Preferred language is required")
    private String preferredLanguage;

    @NotBlank(message = "Communication preference is required")
    private String communicationPreference;

    @NotNull(message = "Marketing consent is required")
    private Boolean marketingConsent;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 