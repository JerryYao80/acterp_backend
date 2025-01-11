package com.arterp.riskservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_warnings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskWarning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // CUSTOMER, BUSINESS, RESOURCE, FINANCIAL

    @Column(nullable = false)
    private String level; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(nullable = false)
    private String message;

    @Column(name = "source_id")
    private Long sourceId; // ID of the related entity (customer, business, etc.)

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String status; // ACTIVE, RESOLVED, IGNORED

    @Column(name = "resolved_by")
    private String resolvedBy;

    @Column(name = "resolution_notes")
    private String resolutionNotes;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 