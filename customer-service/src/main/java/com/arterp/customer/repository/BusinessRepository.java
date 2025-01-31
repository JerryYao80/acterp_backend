package com.arterp.customer.repository;

import com.arterp.customer.entity.Business;
import com.arterp.customer.entity.enums.BusinessPhase;
import com.arterp.customer.entity.enums.BusinessStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByCustomerId(Long customerId);
    
    List<Business> findByCurrentPhase(BusinessPhase phase);
    
    List<Business> findByStatus(BusinessStatus status);
    
    @Query("SELECT b.currentPhase, COUNT(b) FROM Business b GROUP BY b.currentPhase")
    List<Object[]> countByPhase();
    
    @Query("SELECT b.location, COUNT(b) FROM Business b GROUP BY b.location")
    List<Object[]> countByLocation();
    
    @Query("SELECT b.businessType, COUNT(b) FROM Business b GROUP BY b.businessType")
    List<Object[]> countByType();
} 