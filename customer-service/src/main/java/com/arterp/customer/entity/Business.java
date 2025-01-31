package com.arterp.customer.entity;

import com.arterp.customer.entity.enums.BusinessPhase;
import com.arterp.customer.entity.enums.BusinessStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private BusinessPhase currentPhase;

    @Enumerated(EnumType.STRING)
    private BusinessStatus status;

    private String businessType;
    
    private String location;
    
    private BigDecimal totalAmount;

    private LocalDateTime startDate;
    
    private LocalDateTime expectedEndDate;
    
    private LocalDateTime actualEndDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(length = 1000)
    private String remark;
} 