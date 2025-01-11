package com.arterp.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class AssessmentDTO {
    private Long id;
    private Long customerId;

    @NotBlank(message = "Assessment type is required")
    private String assessmentType;

    @NotNull(message = "Assessment date is required")
    private LocalDate assessmentDate;

    @NotBlank(message = "Assessor is required")
    private String assessor;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Result is required")
    private String result;

    private String summary;
    private String recommendations;

    @NotNull(message = "Score is required")
    @Positive(message = "Score must be positive")
    private BigDecimal score;

    @NotBlank(message = "Risk level is required")
    private String riskLevel;

    private String concerns;
    private String strengths;
    private String weaknesses;
    private String requirements;

    private Set<String> documentUrls;
    private String notes;

    @NotBlank(message = "Category is required")
    private String category;

    private LocalDate nextAssessmentDate;

    @NotNull(message = "Requires follow up is required")
    private Boolean requiresFollowUp;

    private String followUpPlan;
} 