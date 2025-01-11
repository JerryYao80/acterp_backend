package com.arterp.resource.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "postnatal_resources")
@DiscriminatorValue("POSTNATAL")
@EqualsAndHashCode(callSuper = true)
public class PostnatalResource extends Resource {

    @Column(nullable = false)
    private String resourceType;  // Center, Hotel, Villa, etc.

    @Column(nullable = false)
    private Integer roomCount;

    @Column(nullable = false)
    private Integer currentOccupancy;

    @Column(length = 1000)
    private String facilities;

    @Column(length = 1000)
    private String services;

    @Column(nullable = false)
    private String licenseNumber;

    @Column(nullable = false)
    private String accreditation;

    @Column(length = 1000)
    private String medicalSupport;

    @Column(length = 1000)
    private String nutritionServices;

    @Column(length = 1000)
    private String careServices;

    @Column(nullable = false)
    private Boolean hasNursery;

    @Column(nullable = false)
    private Boolean hasEmergencySupport;

    @Column(length = 1000)
    private String insuranceInformation;

    @Column(length = 1000)
    private String operatingHours;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private String contactEmail;

    @Column(length = 1000)
    private String roomTypes;

    @Column(length = 1000)
    private String amenities;
} 