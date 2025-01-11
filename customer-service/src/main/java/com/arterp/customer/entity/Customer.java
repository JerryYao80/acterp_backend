package com.arterp.customer.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "customers")
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String idType;  // Passport, ID Card, etc.

    @Column(nullable = false, unique = true)
    private String idNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String maritalStatus;

    @Column(length = 1000)
    private String medicalHistory;

    @Column(length = 1000)
    private String familyHistory;

    @Column(length = 1000)
    private String geneticScreening;

    @Column(nullable = false)
    private String status;  // Active, Inactive, Blocked

    @Column(nullable = false)
    private String customerType;  // Individual, Couple, Agency

    @Column(length = 1000)
    private String requirements;

    @Column(length = 1000)
    private String preferences;

    @Column(nullable = false)
    private String riskLevel;  // Low, Medium, High

    @ElementCollection
    @CollectionTable(name = "customer_addresses", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "address", length = 1000)
    private Set<String> addresses = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "customer_documents", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private Boolean hasInsurance;

    @Column(length = 1000)
    private String insuranceInformation;

    @Column(nullable = false)
    private String source;  // Referral, Website, Social Media, etc.

    @Column(length = 1000)
    private String emergencyContact;

    @Column(nullable = false)
    private String preferredLanguage;

    @Column(nullable = false)
    private String communicationPreference;  // Email, Phone, SMS, etc.

    @Column(nullable = false)
    private Boolean marketingConsent;
} 