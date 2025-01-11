package com.arterp.business.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "business_processes")
@EqualsAndHashCode(callSuper = true)
public class BusinessProcess extends BaseEntity {

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String processType;  // IVF, Surrogacy, etc.

    @Column(nullable = false)
    private String status;  // Planning, In Progress, Completed, etc.

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate expectedEndDate;

    @Column
    private LocalDate actualEndDate;

    @Column(nullable = false)
    private BigDecimal totalBudget;

    @Column(nullable = false)
    private BigDecimal currentSpent;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "business_process_resources", joinColumns = @JoinColumn(name = "process_id"))
    private Set<Long> assignedResourceIds = new HashSet<>();

    @OneToMany(mappedBy = "businessProcess", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessStage> stages = new ArrayList<>();

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private String riskLevel;  // Low, Medium, High

    @ElementCollection
    @CollectionTable(name = "business_process_documents", joinColumns = @JoinColumn(name = "process_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();
} 