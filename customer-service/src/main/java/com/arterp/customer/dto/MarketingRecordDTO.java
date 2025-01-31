package com.arterp.customer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MarketingRecordDTO {
    private Long id;
    private Long businessId;
    private String channel;
    private BigDecimal cost;
    private String conversionSource;
    private Long referralCustomerId;
    private String referralCustomerName;
    private String marketingContent;
    private LocalDateTime effectiveDate;
    private LocalDateTime createdAt;
} 