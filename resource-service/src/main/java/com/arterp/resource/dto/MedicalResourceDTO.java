package com.arterp.resource.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class MedicalResourceDTO extends ResourceDTO {

    @NotBlank(message = "Resource type is required")
    private String resourceType;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @NotBlank(message = "Accreditation is required")
    private String accreditation;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be positive")
    private Integer capacity;

    @NotNull(message = "Current occupancy is required")
    @PositiveOrZero(message = "Current occupancy must not be negative")
    private Integer currentOccupancy;

    private String facilities;
    private String equipment;

    @NotNull(message = "Success rate is required")
    @Min(value = 0, message = "Success rate must be between 0 and 100")
    @Max(value = 100, message = "Success rate must be between 0 and 100")
    private Integer successRate;

    @NotNull(message = "Experience years is required")
    @PositiveOrZero(message = "Experience years must not be negative")
    private Integer experienceYears;

    private String certifications;

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
} 