package com.arterp.business.entity;

import com.arterp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "business_process")
public class BusinessProcess extends BaseEntity {
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "process_type")
    private String processType;

    @Column(name = "status")
    private String status;

    @Column(name = "stage")
    private String stage;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "expected_end_date")
    private LocalDateTime expectedEndDate;

    @Column(name = "actual_end_date")
    private LocalDateTime actualEndDate;

    @ElementCollection
    @CollectionTable(name = "checkup_records", joinColumns = @JoinColumn(name = "business_process_id"))
    @Column(name = "record")
    private List<String> checkupRecords;

    @ElementCollection
    @CollectionTable(name = "notifications", joinColumns = @JoinColumn(name = "business_process_id"))
    @Column(name = "notification")
    private List<String> notifications;

    @ElementCollection
    @CollectionTable(name = "social_media_records", joinColumns = @JoinColumn(name = "business_process_id"))
    @Column(name = "record")
    private List<String> socialMediaRecords;

    @Column(name = "ivf_record")
    private String ivfRecord;

    @Column(name = "embryo_transfer_record")
    private String embryoTransferRecord;

    @Column(name = "pregnancy_care_record")
    private String pregnancyCareRecord;

    @Column(name = "delivery_record")
    private String deliveryRecord;

    @Column(name = "entry_service_record")
    private String entryServiceRecord;

    @Column(name = "settlement_service_record")
    private String settlementServiceRecord;
} 