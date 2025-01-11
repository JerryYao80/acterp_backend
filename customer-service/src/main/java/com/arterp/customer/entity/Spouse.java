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
@Table(name = "spouses")
@EqualsAndHashCode(callSuper = true)
public class Spouse extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String idType;

    @Column(nullable = false, unique = true)
    private String idNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(length = 1000)
    private String medicalHistory;

    @Column(length = 1000)
    private String familyHistory;

    @Column(length = 1000)
    private String geneticScreening;

    @ElementCollection
    @CollectionTable(name = "spouse_documents", joinColumns = @JoinColumn(name = "spouse_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private Boolean hasInsurance;

    @Column(length = 1000)
    private String insuranceInformation;

    @Column(length = 1000)
    private String emergencyContact;

    @Column(nullable = false)
    private String preferredLanguage;

    @Column(nullable = false)
    private String communicationPreference;
} 