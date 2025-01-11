package com.arterp.document.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentDTO {
    private Long id;
    private String name;
    private String type;
    private String contentType;
    private String bucketName;
    private String objectName;
    private Long size;
    private String description;
    private String category;
    private String status;
    private String relatedEntityType;
    private Long relatedEntityId;
    private String ocrContent;
    private String uploadedBy;
    private LocalDateTime uploadedAt;
    private LocalDateTime processedAt;
    private String processedBy;
    private String tags;
    private String url;  // Presigned URL for downloading
} 