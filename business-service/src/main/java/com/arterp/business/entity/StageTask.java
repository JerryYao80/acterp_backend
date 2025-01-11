package com.arterp.business.entity;

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
@Table(name = "stage_tasks")
@EqualsAndHashCode(callSuper = true)
public class StageTask extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    private ProcessStage stage;

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
    @CollectionTable(name = "stage_task_resources", joinColumns = @JoinColumn(name = "task_id"))
    private Set<Long> assignedResourceIds = new HashSet<>();

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String notes;

    @ElementCollection
    @CollectionTable(name = "stage_task_documents", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();

    @Column(nullable = false)
    private String taskType;  // Medical Exam, Medication, Surgery, etc.

    @Column
    private String result;  // Task result or outcome

    @Column
    private String nextAction;  // Next action to be taken based on the result
} 