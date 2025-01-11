package com.arterp.resource.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "donor_resources")
@DiscriminatorValue("DONOR")
@EqualsAndHashCode(callSuper = true)
public class DonorResource extends Resource {

    @Column(nullable = false)
    private String donorType;  // Egg, Sperm

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

    @Column
    private String eyeColor;

    @Column
    private String hairColor;

    @Column
    private String skinTone;

    @Column(length = 1000)
    private String personalityTraits;

    @Column(length = 1000)
    private String specialTalents;

    @Column(nullable = false)
    private Integer donationCount;

    @Column(nullable = false)
    private Integer successfulDonations;
} 