package com.arterp.customer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "marketing_records")
public class MarketingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    private String channel;
    
    private BigDecimal cost;
    
    private String conversionSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referral_customer_id")
    private Customer referralCustomer;

    @Column(length = 1000)
    private String marketingContent;

    private LocalDateTime effectiveDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
} 