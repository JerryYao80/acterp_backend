package com.arterp.customer.entity;

import com.arterp.customer.entity.enums.BusinessPhase;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "business_status_records")
public class BusinessStatusRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    @Enumerated(EnumType.STRING)
    private BusinessPhase previousPhase;

    @Enumerated(EnumType.STRING)
    private BusinessPhase currentPhase;

    private String operator;

    @Column(length = 1000)
    private String remark;

    private Boolean isAbnormal;

    @Column(length = 1000)
    private String abnormalReason;

    @Column(length = 1000)
    private String solution;

    @CreationTimestamp
    private LocalDateTime createdAt;
} 