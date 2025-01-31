package com.arterp.customer.dto;

import com.arterp.customer.entity.enums.BusinessPhase;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BusinessStatusRecordDTO {
    private Long id;
    private Long businessId;
    private BusinessPhase previousPhase;
    private BusinessPhase currentPhase;
    private String operator;
    private String remark;
    private Boolean isAbnormal;
    private String abnormalReason;
    private String solution;
    private LocalDateTime createdAt;
} 