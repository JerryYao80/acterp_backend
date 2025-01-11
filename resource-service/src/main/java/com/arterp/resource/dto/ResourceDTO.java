package com.arterp.resource.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public abstract class ResourceDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Cost is required")
    @Positive(message = "Cost must be positive")
    private BigDecimal cost;

    @NotBlank(message = "Quality is required")
    private String quality;

    private String description;
    private Set<String> tags;
    private Set<String> documentUrls;
    private String notes;

    @NotNull(message = "Available from date is required")
    private LocalDate availableFrom;

    private LocalDate availableTo;

    @NotBlank(message = "Risk level is required")
    private String riskLevel;
} 