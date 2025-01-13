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

    @NotBlank(message = "ID type is required")
    private String idType;

    @NotBlank(message = "ID number is required")
    private String idNumber;

    @NotBlank(message = "Phone number is required")
    private String phone;

    private String occupation;
    private String address;
    private String maritalStatus;
    private Double budget;
    private LocalDate expectedStartTime;
    private LocalDate expectedEndTime;
    private String donorRequirement;
    private String gestationRequirement;
    private String recommendedPlan;
    private String medicalHistory;
    private String familyHistory;
    private String status;
    private String emergencyContact;
    private String source;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 