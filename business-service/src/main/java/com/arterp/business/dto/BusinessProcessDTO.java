package com.arterp.business.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class BusinessProcessDTO {
    private Long id;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Process type is required")
    private String processType;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate expectedEndDate;
    private LocalDate actualEndDate;

    @NotNull(message = "Total budget is required")
    @Positive(message = "Total budget must be positive")
    private BigDecimal totalBudget;

    @NotNull(message = "Current spent is required")
    @Positive(message = "Current spent must be positive")
    private BigDecimal currentSpent;

    private Set<Long> assignedResourceIds;

    @Valid
    private List<ProcessStageDTO> stages;

    private String notes;

    @NotBlank(message = "Risk level is required")
    private String riskLevel;

    private Set<String> documentUrls;
} 