package com.arterp.business.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BusinessProcessDTO {
    private Long id;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Process type is required")
    private String processType;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Stage is required")
    private String stage;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    private LocalDateTime expectedEndDate;
    private LocalDateTime actualEndDate;
    private List<String> checkupRecords;
    private List<String> notifications;
    private List<String> socialMediaRecords;
    private String ivfRecord;
    private String embryoTransferRecord;
    private String pregnancyCareRecord;
    private String deliveryRecord;
    private String entryServiceRecord;
    private String settlementServiceRecord;
    private boolean deleted;
} 