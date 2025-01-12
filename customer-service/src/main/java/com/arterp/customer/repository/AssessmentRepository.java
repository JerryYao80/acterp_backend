package com.arterp.customer.repository;

import com.arterp.customer.entity.Assessment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    Page<Assessment> findByCustomer_Id(Long customerId, Pageable pageable);

    @Query("SELECT a FROM Assessment a WHERE " +
            "a.customer.id = :customerId AND " +
            "(LOWER(a.assessmentType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.result) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Assessment> searchByCustomerId(Long customerId, String search, Pageable pageable);

    @Query("SELECT a.assessmentType, COUNT(a) FROM Assessment a GROUP BY a.assessmentType")
    List<Object[]> countByAssessmentType();

    @Query("SELECT a.status, COUNT(a) FROM Assessment a GROUP BY a.status")
    List<Object[]> countByStatus();

    @Query("SELECT a.result, COUNT(a) FROM Assessment a GROUP BY a.result")
    List<Object[]> countByResult();

    @Query("SELECT a.riskLevel, COUNT(a) FROM Assessment a GROUP BY a.riskLevel")
    List<Object[]> countByRiskLevel();

    @Query("SELECT a.category, COUNT(a) FROM Assessment a GROUP BY a.category")
    List<Object[]> countByCategory();

    @Query("SELECT a.assessor, COUNT(a) FROM Assessment a GROUP BY a.assessor")
    List<Object[]> countByAssessor();
} 