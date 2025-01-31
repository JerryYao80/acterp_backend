package com.arterp.customer.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MedicalRecordDTO {
    private Long id;
    private Long businessId;
    private String recordType;
    private String hospitalName;
    private String doctorName;
    private LocalDateTime checkDate;
    private String reportContent;
    private List<String> reportFiles;
    private String ocrResults;
    private List<String> abnormalFlags;
    private String followUpActions;
    private LocalDateTime createdAt;
} 