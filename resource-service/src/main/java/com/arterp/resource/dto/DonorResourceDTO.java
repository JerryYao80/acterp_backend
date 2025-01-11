package com.arterp.resource.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class DonorResourceDTO extends ResourceDTO {

    @NotBlank(message = "Donor type is required")
    private String donorType;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Blood type is required")
    private String bloodType;

    @NotBlank(message = "Ethnicity is required")
    private String ethnicity;

    @NotBlank(message = "Education is required")
    private String education;

    @NotNull(message = "Height is required")
    private Integer height;

    @NotNull(message = "Weight is required")
    private Integer weight;

    private String medicalHistory;
    private String familyHistory;
    private String geneticScreening;
    private String eyeColor;
    private String hairColor;
    private String skinTone;
    private String personalityTraits;
    private String specialTalents;

    @NotNull(message = "Donation count is required")
    @PositiveOrZero(message = "Donation count must not be negative")
    private Integer donationCount;

    @NotNull(message = "Successful donations is required")
    @PositiveOrZero(message = "Successful donations must not be negative")
    private Integer successfulDonations;
} 