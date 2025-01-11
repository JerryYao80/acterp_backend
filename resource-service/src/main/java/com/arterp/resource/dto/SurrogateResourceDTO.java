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
public class SurrogateResourceDTO extends ResourceDTO {

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

    @NotNull(message = "Pregnancy count is required")
    @PositiveOrZero(message = "Pregnancy count must not be negative")
    private Integer pregnancyCount;

    @NotNull(message = "Successful pregnancies is required")
    @PositiveOrZero(message = "Successful pregnancies must not be negative")
    private Integer successfulPregnancies;

    @NotNull(message = "Surrogacy count is required")
    @PositiveOrZero(message = "Surrogacy count must not be negative")
    private Integer surrogacyCount;

    @NotNull(message = "Successful surrogacies is required")
    @PositiveOrZero(message = "Successful surrogacies must not be negative")
    private Integer successfulSurrogacies;

    private String pregnancyHistory;

    @NotBlank(message = "Marital status is required")
    private String maritalStatus;

    @NotNull(message = "Partner support status is required")
    private Boolean hasPartnerSupport;

    private String lifestyleInformation;

    @NotBlank(message = "Employment status is required")
    private String employmentStatus;

    private String psychologicalEvaluation;

    @NotNull(message = "Insurance status is required")
    private Boolean hasInsurance;
} 