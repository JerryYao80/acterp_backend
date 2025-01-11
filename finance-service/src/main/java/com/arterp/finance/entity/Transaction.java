package com.arterp.finance.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {
    @Column(nullable = false)
    private String type;  // Income, Expense

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String status;  // Pending, Completed, Failed

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(length = 1000)
    private String description;

    @Column
    private Long relatedBusinessId;

    @Column
    private Long relatedCustomerId;

    @Column
    private Long relatedResourceId;

    @Column(nullable = false)
    private String paymentMethod;

    @Column
    private String paymentReference;

    @ElementCollection
    @CollectionTable(name = "transaction_documents", joinColumns = @JoinColumn(name = "transaction_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private String createdBy;

    @Column
    private String approvedBy;
} 