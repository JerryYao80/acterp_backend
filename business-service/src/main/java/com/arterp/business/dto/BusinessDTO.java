package com.arterp.business.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BusinessDTO {
    private Long id;
    private Long customerId;
    private String type;
    private String status;
    private String stage;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
    private LocalDateTime actualEndDate;
    private String description;
    private String remark;
    private Integer priority;
    private Integer difficulty;
    private Integer value;
    private Integer progress;
    private Double cost;
    private Double revenue;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
} 