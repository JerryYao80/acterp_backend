package com.arterp.resource.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostnatalResourceDTO extends ResourceDTO {

    @NotBlank(message = "Resource type is required")
    private String resourceType;

    @NotNull(message = "Room count is required")
    @Positive(message = "Room count must be positive")
    private Integer roomCount;

    @NotNull(message = "Current occupancy is required")
    @PositiveOrZero(message = "Current occupancy must not be negative")
    private Integer currentOccupancy;

    private String facilities;
    private String services;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @NotBlank(message = "Accreditation is required")
    private String accreditation;

    private String medicalSupport;
    private String nutritionServices;
    private String careServices;

    @NotNull(message = "Nursery status is required")
    private Boolean hasNursery;

    @NotNull(message = "Emergency support status is required")
    private Boolean hasEmergencySupport;

    private String insuranceInformation;
    private String operatingHours;

    @NotBlank(message = "Contact person is required")
    private String contactPerson;

    @NotBlank(message = "Contact phone is required")
    private String contactPhone;

    @NotBlank(message = "Contact email is required")
    @Email(message = "Contact email must be valid")
    private String contactEmail;

    private String roomTypes;
    private String amenities;
} 