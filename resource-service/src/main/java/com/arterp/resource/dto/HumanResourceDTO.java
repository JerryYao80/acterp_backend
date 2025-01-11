package com.arterp.resource.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class HumanResourceDTO extends ResourceDTO {

    @NotBlank(message = "Resource type is required")
    private String resourceType;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Education is required")
    private String education;

    private String qualifications;
    private String certifications;

    @NotNull(message = "Experience years is required")
    @PositiveOrZero(message = "Experience years must not be negative")
    private Integer experienceYears;

    @NotBlank(message = "Languages is required")
    private String languages;

    private String expertise;

    @NotNull(message = "Success rate is required")
    @Min(value = 0, message = "Success rate must be between 0 and 100")
    @Max(value = 100, message = "Success rate must be between 0 and 100")
    private Integer successRate;

    private String workHistory;
    private String availability;

    @NotBlank(message = "Working hours is required")
    private String workingHours;

    @NotBlank(message = "Contact phone is required")
    private String contactPhone;

    @NotBlank(message = "Contact email is required")
    @Email(message = "Contact email must be valid")
    private String contactEmail;

    private String performanceMetrics;

    @NotNull(message = "Full time status is required")
    private Boolean isFullTime;
} 