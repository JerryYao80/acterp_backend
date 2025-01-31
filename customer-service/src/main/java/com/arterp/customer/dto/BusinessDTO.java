package com.arterp.customer.dto;

import com.arterp.customer.entity.enums.BusinessPhase;
import com.arterp.customer.entity.enums.BusinessStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BusinessDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private BusinessPhase currentPhase;
    private BusinessStatus status;
    private String businessType;
    private String location;
    private BigDecimal totalAmount;
    private LocalDateTime startDate;
    private LocalDateTime expectedEndDate;
    private LocalDateTime actualEndDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String remark;
} 