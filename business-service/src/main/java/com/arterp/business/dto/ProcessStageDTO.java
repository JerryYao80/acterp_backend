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
public class ProcessStageDTO {
    private Long id;

    @NotBlank(message = "Stage name is required")
    private String name;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Sequence is required")
    private Integer sequence;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate expectedEndDate;
    private LocalDate actualEndDate;

    @NotNull(message = "Budget is required")
    @Positive(message = "Budget must be positive")
    private BigDecimal budget;

    @NotNull(message = "Spent amount is required")
    @Positive(message = "Spent amount must be positive")
    private BigDecimal spent;

    private Set<Long> assignedResourceIds;

    @Valid
    private List<StageTaskDTO> tasks;

    private String notes;
    private Set<String> documentUrls;
} 