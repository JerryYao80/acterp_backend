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
@Table(name = "process_stages")
@EqualsAndHashCode(callSuper = true)
public class ProcessStage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_process_id", nullable = false)
    private BusinessProcess businessProcess;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;  // Not Started, In Progress, Completed, etc.

    @Column(nullable = false)
    private Integer sequence;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate expectedEndDate;

    @Column
    private LocalDate actualEndDate;

    @Column(nullable = false)
    private BigDecimal budget;

    @Column(nullable = false)
    private BigDecimal spent;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "process_stage_resources", joinColumns = @JoinColumn(name = "stage_id"))
    private Set<Long> assignedResourceIds = new HashSet<>();

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StageTask> tasks = new ArrayList<>();

    @Column(length = 1000)
    private String notes;

    @ElementCollection
    @CollectionTable(name = "process_stage_documents", joinColumns = @JoinColumn(name = "stage_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();
} 