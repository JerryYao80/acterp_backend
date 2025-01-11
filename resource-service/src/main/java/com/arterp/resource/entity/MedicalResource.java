package com.arterp.resource.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medical_resources")
@DiscriminatorValue("MEDICAL")
@EqualsAndHashCode(callSuper = true)
public class MedicalResource extends Resource {

    @Column(nullable = false)
    private String resourceType;  // Hospital, Clinic, Laboratory, etc.

    @Column(nullable = false)
    private String specialization;  // IVF, Embryology, Obstetrics, etc.

    @Column(nullable = false)
    private String licenseNumber;

    @Column(nullable = false)
    private String accreditation;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer currentOccupancy;

    @Column(length = 1000)
    private String facilities;

    @Column(length = 1000)
    private String equipment;

    @Column(nullable = false)
    private Integer successRate;  // percentage

    @Column(nullable = false)
    private Integer experienceYears;

    @Column(length = 1000)
    private String certifications;

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
} 