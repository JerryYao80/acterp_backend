package com.arterp.customer.repository;

import com.arterp.customer.entity.MarketingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketingRecordRepository extends JpaRepository<MarketingRecord, Long> {
    List<MarketingRecord> findByBusinessId(Long businessId);
    
    @Query("SELECT m.channel, COUNT(m) FROM MarketingRecord m GROUP BY m.channel")
    List<Object[]> countByChannel();
    
    @Query("SELECT m.conversionSource, COUNT(m) FROM MarketingRecord m GROUP BY m.conversionSource")
    List<Object[]> countByConversionSource();
} 