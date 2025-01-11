package com.arterp.document.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documents")
@EqualsAndHashCode(callSuper = true)
public class Document extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;  // PDF, Image, Word, etc.

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String bucketName;

    @Column(nullable = false)
    private String objectName;

    @Column(nullable = false)
    private Long size;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String category;  // Contract, Report, Invoice, etc.

    @Column(nullable = false)
    private String status;  // Active, Archived, Deleted

    @Column
    private String relatedEntityType;  // Customer, Business, Resource, Finance

    @Column
    private Long relatedEntityId;

    @Column(length = 4000)
    private String ocrContent;  // Extracted text content from OCR

    @Column(nullable = false)
    private String uploadedBy;

    @Column
    private LocalDateTime processedAt;

    @Column
    private String processedBy;

    @Column
    private String tags;  // Comma-separated tags
} 