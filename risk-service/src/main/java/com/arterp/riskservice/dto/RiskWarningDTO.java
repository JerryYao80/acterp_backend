package com.arterp.riskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskWarningDTO {
    private Long id;
    private String type;
    private String level;
    private String message;
    private Long sourceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private String resolvedBy;
    private String resolutionNotes;
} 