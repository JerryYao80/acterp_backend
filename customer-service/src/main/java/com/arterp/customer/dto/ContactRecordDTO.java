package com.arterp.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ContactRecordDTO {
    private Long id;
    private Long customerId;

    @NotBlank(message = "Contact type is required")
    private String contactType;

    @NotNull(message = "Contact time is required")
    private LocalDateTime contactTime;

    @NotBlank(message = "Direction is required")
    private String direction;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Purpose is required")
    private String purpose;

    private String summary;
    private String nextAction;
    private LocalDateTime nextContactTime;

    @NotBlank(message = "Assigned to is required")
    private String assignedTo;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer duration;

    @NotBlank(message = "Result is required")
    private String result;

    private Set<String> documentUrls;
    private String notes;

    @NotBlank(message = "Channel is required")
    private String channel;

    @NotBlank(message = "Priority is required")
    private String priority;

    @NotNull(message = "Requires follow up is required")
    private Boolean requiresFollowUp;
} 