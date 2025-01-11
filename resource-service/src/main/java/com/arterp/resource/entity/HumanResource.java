package com.arterp.resource.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "human_resources")
@DiscriminatorValue("HUMAN")
@EqualsAndHashCode(callSuper = true)
public class HumanResource extends Resource {

    @Column(nullable = false)
    private String resourceType;  // Doctor, Nurse, Counselor, etc.

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String licenseNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String education;

    @Column(length = 1000)
    private String qualifications;

    @Column(length = 1000)
    private String certifications;

    @Column(nullable = false)
    private Integer experienceYears;

    @Column(nullable = false)
    private String languages;

    @Column(length = 1000)
    private String expertise;

    @Column(nullable = false)
    private Integer successRate;  // percentage

    @Column(length = 1000)
    private String workHistory;

    @Column(length = 1000)
    private String availability;

    @Column(nullable = false)
    private String workingHours;

    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private String contactEmail;

    @Column(length = 1000)
    private String performanceMetrics;

    @Column(nullable = false)
    private Boolean isFullTime;
} 