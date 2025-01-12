package com.arterp.customer.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(name = "id_type", nullable = false)
    private String idType;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "medical_history", columnDefinition = "TEXT")
    private String medicalHistory;

    @Column(name = "family_history", columnDefinition = "TEXT")
    private String familyHistory;

    @Column(name = "genetic_screening", columnDefinition = "TEXT")
    private String geneticScreening;

    @Column(nullable = false)
    private String status;

    @Column(name = "customer_type", nullable = false)
    private String customerType;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    @Column(columnDefinition = "TEXT")
    private String preferences;

    @Column(name = "risk_level", nullable = false)
    private String riskLevel;

    @ElementCollection
    @CollectionTable(name = "customer_addresses", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "address")
    private List<String> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "customer_documents", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "document_url")
    private List<String> documentUrls = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "has_insurance")
    private boolean hasInsurance;

    @Column(name = "insurance_information", columnDefinition = "TEXT")
    private String insuranceInformation;

    private String source;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @Column(name = "communication_preference")
    private String communicationPreference;

    @Column(name = "marketing_consent")
    private boolean marketingConsent;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 