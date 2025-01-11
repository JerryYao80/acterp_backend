package com.arterp.resource.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "surrogate_resources")
@DiscriminatorValue("SURROGATE")
@EqualsAndHashCode(callSuper = true)
public class SurrogateResource extends Resource {

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = false)
    private String ethnicity;

    @Column(nullable = false)
    private String education;

    @Column(nullable = false)
    private Integer height;  // in cm

    @Column(nullable = false)
    private Integer weight;  // in kg

    @Column(length = 1000)
    private String medicalHistory;

    @Column(length = 1000)
    private String familyHistory;

    @Column(length = 1000)
    private String geneticScreening;

    @Column(nullable = false)
    private Integer pregnancyCount;

    @Column(nullable = false)
    private Integer successfulPregnancies;

    @Column(nullable = false)
    private Integer surrogacyCount;

    @Column(nullable = false)
    private Integer successfulSurrogacies;

    @Column(length = 1000)
    private String pregnancyHistory;

    @Column(nullable = false)
    private String maritalStatus;

    @Column(nullable = false)
    private Boolean hasPartnerSupport;

    @Column(length = 1000)
    private String lifestyleInformation;

    @Column(nullable = false)
    private String employmentStatus;

    @Column(length = 1000)
    private String psychologicalEvaluation;

    @Column(nullable = false)
    private Boolean hasInsurance;
} 