package com.arterp.customer.repository;

import com.arterp.customer.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByBusinessId(Long businessId);
    
    List<MedicalRecord> findByBusinessIdAndRecordType(Long businessId, String recordType);
    
    List<MedicalRecord> findByBusinessIdAndAbnormalFlagsIsNotEmpty(Long businessId);
} 