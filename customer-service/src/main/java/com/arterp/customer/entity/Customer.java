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
    private String address;

    @Column(nullable = false)
    private String occupation;

    @Column(name = "id_type", nullable = false)
    private String idType;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Column(name = "customer_type", nullable = false)
    private String customerType;

    private String source;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "donor_requirement", columnDefinition = "TEXT")
    private String donorRequirement;

    @Column(name = "gestation_requirement", columnDefinition = "TEXT")
    private String gestationRequirement;

    @Column(nullable = false)
    private Double budget;

    @Column(name = "expected_start_time")
    private LocalDate expectedStartTime;

    @Column(name = "expected_end_time")
    private LocalDate expectedEndTime;

    @Column(name = "recommended_plan")
    private String recommendedPlan;

    @Column(name = "medical_history", columnDefinition = "TEXT")
    private String medicalHistory;

    @Column(name = "family_history", columnDefinition = "TEXT")
    private String familyHistory;

    @Column(nullable = false)
    private String status;

    @Column(name = "risk_level", nullable = false)
    private String riskLevel;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 