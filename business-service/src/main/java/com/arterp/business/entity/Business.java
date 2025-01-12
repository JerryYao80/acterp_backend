package com.arterp.business.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "business")
@EqualsAndHashCode(callSuper = true)
public class Business extends BaseEntity {
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "stage")
    private String stage;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "expected_end_date")
    private LocalDateTime expectedEndDate;

    @Column(name = "actual_end_date")
    private LocalDateTime actualEndDate;

    @Column(name = "description")
    private String description;

    @Column(name = "remark")
    private String remark;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "value")
    private Integer value;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "revenue")
    private Double revenue;
} 