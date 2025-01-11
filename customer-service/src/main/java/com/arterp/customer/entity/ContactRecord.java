package com.arterp.customer.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "contact_records")
@EqualsAndHashCode(callSuper = true)
public class ContactRecord extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String contactType;  // Call, Email, Meeting, etc.

    @Column(nullable = false)
    private LocalDateTime contactTime;

    @Column(nullable = false)
    private String direction;  // Inbound, Outbound

    @Column(nullable = false)
    private String status;  // Scheduled, Completed, Cancelled, etc.

    @Column(nullable = false)
    private String purpose;  // Inquiry, Follow-up, Complaint, etc.

    @Column(length = 1000)
    private String summary;

    @Column(length = 1000)
    private String nextAction;

    @Column
    private LocalDateTime nextContactTime;

    @Column(nullable = false)
    private String assignedTo;

    @Column(nullable = false)
    private Integer duration;  // in minutes

    @Column(nullable = false)
    private String result;  // Successful, Failed, Pending, etc.

    @ElementCollection
    @CollectionTable(name = "contact_record_documents", joinColumns = @JoinColumn(name = "contact_record_id"))
    @Column(name = "document_url")
    private Set<String> documentUrls = new HashSet<>();

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private String channel;  // Phone, Email, In-person, Video Call, etc.

    @Column(nullable = false)
    private String priority;  // High, Medium, Low

    @Column(nullable = false)
    private Boolean requiresFollowUp;
} 