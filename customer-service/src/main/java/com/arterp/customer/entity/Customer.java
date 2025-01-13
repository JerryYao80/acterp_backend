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

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(name = "birthday")
    private LocalDate birthDate;

    @Column(name = "id_type", length = 20)
    private String idType;

    @Column(name = "id_number", length = 50)
    private String idNumber;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String occupation;

    @Column(columnDefinition = "text")
    private String address;

    @Column(name = "marital_status", length = 20)
    private String maritalStatus;

    @Column(precision = 15, scale = 2)
    private Double budget;

    @Column(name = "expected_start_time")
    private LocalDate expectedStartTime;

    @Column(name = "expected_end_time")
    private LocalDate expectedEndTime;

    @Column(name = "donor_requirement", columnDefinition = "text")
    private String donorRequirement;

    @Column(name = "gestation_requirement", columnDefinition = "text")
    private String gestationRequirement;

    @Column(name = "recommended_plan")
    private String recommendedPlan;

    @Column(name = "medical_history", columnDefinition = "text")
    private String medicalHistory;

    @Column(name = "family_history", columnDefinition = "text")
    private String familyHistory;

    @Column(length = 20)
    private String status;

    @Column(name = "emergency_contact", columnDefinition = "text")
    private String emergencyContact;

    @Column(length = 100)
    private String source;

    @Column(columnDefinition = "text")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 