package com.arterp.customer.repository;

import com.arterp.customer.entity.BusinessStatusRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessStatusRecordRepository extends JpaRepository<BusinessStatusRecord, Long> {
    List<BusinessStatusRecord> findByBusinessId(Long businessId);
    
    List<BusinessStatusRecord> findByBusinessIdAndIsAbnormalTrue(Long businessId);
} 