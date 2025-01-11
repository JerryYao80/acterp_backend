package com.arterp.customer.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "assessments")
@EqualsAndHashCode(callSuper = true)
public class Assessment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String assessmentType;  // Medical, Financial, Psychological, etc.

    @Column(nullable = false)
    private LocalDate assessmentDate;

    @Column(nullable = false)
    private String assessor;

    @Column(nullable = false)
    private String status;  // Pending, Completed, Failed, etc.

    @Column(nullable = false)
    private String result;  // Passed, Failed, Conditional

    @Column(length = 1000)
    private String summary;

    @Column(length = 1000)
    private String recommendations;

    @Column(nullable = false)
    private BigDecimal score;

    @Column(nullable = false)
    private String riskLevel;  // Low, Medium, High

    @Column(length = 1000)
    private String concerns;

    @Column(length = 1000)
    private String strengths;

    @Column(length = 1000)
    private String weaknesses;

    @Column(length = 1000)
    private String requirements;

    @ElementCollection
    @CollectionTable(name = "assessment_documents", joinColumns = @JoinColumn(name = "assessment_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private String category;  // Primary, Follow-up, Re-assessment

    @Column
    private LocalDate nextAssessmentDate;

    @Column(nullable = false)
    private Boolean requiresFollowUp;

    @Column(length = 1000)
    private String followUpPlan;
} 