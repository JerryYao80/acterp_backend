package com.arterp.customer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    private String recordType;
    
    private String hospitalName;
    
    private String doctorName;
    
    private LocalDateTime checkDate;

    @Column(length = 2000)
    private String reportContent;

    @ElementCollection
    @CollectionTable(name = "medical_record_files", joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "file_path")
    private List<String> reportFiles;

    @Column(columnDefinition = "jsonb")
    private String ocrResults;

    @ElementCollection
    @CollectionTable(name = "medical_record_abnormal_flags", joinColumns = @JoinColumn(name = "medical_record_id"))
    @Column(name = "flag")
    private List<String> abnormalFlags;

    @Column(length = 1000)
    private String followUpActions;

    @CreationTimestamp
    private LocalDateTime createdAt;
} 