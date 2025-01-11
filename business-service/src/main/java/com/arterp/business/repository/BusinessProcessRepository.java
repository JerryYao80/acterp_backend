package com.arterp.business.repository;

import com.arterp.business.entity.BusinessProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BusinessProcessRepository extends JpaRepository<BusinessProcess, Long> {
    Page<BusinessProcess> findByCustomerId(Long customerId, Pageable pageable);

    @Query("SELECT b FROM BusinessProcess b WHERE " +
            "b.customerId = :customerId AND " +
            "LOWER(b.processType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(b.status) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BusinessProcess> searchByCustomerId(Long customerId, String search, Pageable pageable);

    @Query("SELECT b.processType, COUNT(b) FROM BusinessProcess b GROUP BY b.processType")
    List<Object[]> countByProcessType();

    @Query("SELECT b.status, COUNT(b) FROM BusinessProcess b GROUP BY b.status")
    List<Object[]> countByStatus();

    @Query("SELECT b.riskLevel, COUNT(b) FROM BusinessProcess b GROUP BY b.riskLevel")
    List<Object[]> countByRiskLevel();

    long countByStatus(String status);

    long countByCreatedAtAfter(LocalDateTime date);
} 