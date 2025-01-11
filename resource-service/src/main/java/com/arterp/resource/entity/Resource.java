package com.arterp.resource.entity;

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
@Table(name = "resources")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "resource_type")
@EqualsAndHashCode(callSuper = true)
public abstract class Resource extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;  // Available, Reserved, In Use, Unavailable

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private String quality;  // A, B, C, D

    @Column(length = 1000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "resource_tags", joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "resource_documents", joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private LocalDate availableFrom;

    @Column
    private LocalDate availableTo;

    @Column(nullable = false)
    private String riskLevel;  // Low, Medium, High
} 